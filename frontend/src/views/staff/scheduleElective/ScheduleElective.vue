<template>
  <a-card :bordered="false" class="card-area">
    <div :class="advanced ? 'search' : null">
      <!-- 搜索区域 -->
      <a-form layout="horizontal">
        <a-row :gutter="15">
          <div :class="advanced ? null: 'fold'">
            <a-col :md="6" :sm="24">
              <a-form-item
                label="课程名称"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}">
                <a-input v-model="queryParams.courseName"/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item
                label="专业名称"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}">
                <a-input v-model="queryParams.majorName"/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item
                label="代课老师"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}">
                <a-input v-model="queryParams.staffName"/>
              </a-form-item>
            </a-col>
          </div>
          <span style="float: right; margin-top: 3px;">
            <a-button type="primary" @click="search">查询</a-button>
            <a-button style="margin-left: 8px" @click="reset">重置</a-button>
          </span>
        </a-row>
      </a-form>
    </div>
    <div>
      <div class="operator">
<!--        <a-button type="primary" ghost @click="add">新增</a-button>-->
<!--        <a-button @click="batchDelete">删除</a-button>-->
      </div>
      <!-- 表格区域 -->
      <a-table ref="TableInfo"
               :columns="columns"
               :rowKey="record => record.id"
               :dataSource="dataSource"
               :pagination="pagination"
               :loading="loading"
               :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
               :scroll="{ x: 900 }"
               @change="handleTableChange">
        <template slot="operation" slot-scope="text, record">
          <a-icon v-if="record.status == 0" type="folder-open" theme="twoTone" twoToneColor="#4a9ff5" @click="scoreViewOpen(record)" title="填写成绩"></a-icon>
          <a-icon type="file-search" @click="dishesViewOpen(record)" title="详 情" style="margin-left: 15px"></a-icon>
        </template>
      </a-table>
    </div>
    <dishes-add
      v-if="dishesAdd.visiable"
      @close="handledishesAddClose"
      @success="handledishesAddSuccess"
      :dishesAddVisiable="dishesAdd.visiable">
    </dishes-add>
    <dishes-edit
      ref="dishesEdit"
      @close="handledishesEditClose"
      @success="handledishesEditSuccess"
      :dishesEditVisiable="dishesEdit.visiable">
    </dishes-edit>
    <dishes-view
      @close="handledishesViewClose"
      :dishesShow="dishesView.visiable"
      :dishesData="dishesView.data">
    </dishes-view>
    <score-view
      @close="handlescheduleClassScoreViewClose"
      @success="handlescheduleClassScoreViewSuccess"
      :dishesShow="scheduleClassScoreView.visiable"
      :dishesData="scheduleClassScoreView.data">
    </score-view>
  </a-card>
</template>

<script>
import RangeDate from '@/components/datetime/RangeDate'
import dishesAdd from './ScheduleElectiveAdd.vue'
import dishesEdit from './ScheduleElectiveEdit.vue'
import dishesView from './ScheduleElectiveView.vue'
import scoreView from './ScheduleElectivScoreView.vue'
import {mapState} from 'vuex'
import moment from 'moment'
moment.locale('zh-cn')

export default {
  name: 'dishes',
  components: {dishesAdd, dishesEdit, dishesView, RangeDate, scoreView},
  data () {
    return {
      advanced: false,
      dishesAdd: {
        visiable: false
      },
      dishesEdit: {
        visiable: false
      },
      dishesView: {
        visiable: false,
        data: null
      },
      scheduleClassScoreView: {
        visiable: false,
        data: null
      },
      queryParams: {},
      filteredInfo: null,
      sortedInfo: null,
      paginationInfo: null,
      dataSource: [],
      selectedRowKeys: [],
      loading: false,
      pagination: {
        pageSizeOptions: ['10', '20', '30', '40', '100'],
        defaultCurrent: 1,
        defaultPageSize: 10,
        showQuickJumper: true,
        showSizeChanger: true,
        showTotal: (total, range) => `显示 ${range[0]} ~ ${range[1]} 条记录，共 ${total} 条记录`
      },
      userList: []
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    columns () {
      return [{
        title: '课程时间',
        ellipsis: true,
        dataIndex: 'courseDate'
      }, {
        title: '开始结束时间',
        ellipsis: true,
        dataIndex: 'startTime',
        customRender: (text, row, index) => {
          if (text !== null) {
            return row.startTime + ' - ' + row.endTime
          } else {
            return '- -'
          }
        }
      }, {
        title: '课程名称',
        ellipsis: true,
        dataIndex: 'courseName'
      }, {
        title: '上课地址',
        ellipsis: true,
        dataIndex: 'address'
      }, {
        title: '导师姓名',
        ellipsis: true,
        dataIndex: 'staffName'
      }, {
        title: '代课老师头像',
        dataIndex: 'images',
        customRender: (text, record, index) => {
          if (!record.staffImages) return <a-avatar shape="square" icon="user" />
          return <a-popover>
            <template slot="content">
              <a-avatar shape="square" size={132} icon="user" src={ 'http://127.0.0.1:9527/imagesWeb/' + record.staffImages.split(',')[0] } />
            </template>
            <a-avatar shape="square" icon="user" src={ 'http://127.0.0.1:9527/imagesWeb/' + record.staffImages.split(',')[0] } />
          </a-popover>
        }
      }, {
        title: '所属专业',
        ellipsis: true,
        dataIndex: 'majorName',
        customRender: (text, row, index) => {
          if (text !== null) {
            return text
          } else {
            return '- -'
          }
        }
      }, {
        title: '备注',
        ellipsis: true,
        dataIndex: 'content',
        customRender: (text, row, index) => {
          if (text !== null) {
            return text
          } else {
            return '- -'
          }
        }
      }, {
        title: '创建时间',
        ellipsis: true,
        dataIndex: 'createDate',
        customRender: (text, row, index) => {
          if (text !== null) {
            return text
          } else {
            return '- -'
          }
        }
      }, {
        title: '操作',
        dataIndex: 'operation',
        scopedSlots: {customRender: 'operation'}
      }]
    }
  },
  mounted () {
    this.fetch()
  },
  methods: {
    dishesViewOpen (row) {
      this.dishesView.data = row
      this.dishesView.visiable = true
    },
    scoreViewOpen (row) {
      this.scheduleClassScoreView.data = row
      this.scheduleClassScoreView.visiable = true
    },
    handlescheduleClassScoreViewClose () {
      this.scheduleClassScoreView.visiable = false
    },
    handlescheduleClassScoreViewSuccess () {
      this.scheduleClassScoreView.visiable = false
      this.$message.success('成绩添加成功')
      this.search()
    },
    handledishesViewClose () {
      this.dishesView.visiable = false
    },
    onSelectChange (selectedRowKeys) {
      this.selectedRowKeys = selectedRowKeys
    },
    toggleAdvanced () {
      this.advanced = !this.advanced
    },
    add () {
      this.dishesAdd.visiable = true
    },
    handledishesAddClose () {
      this.dishesAdd.visiable = false
    },
    handledishesAddSuccess () {
      this.dishesAdd.visiable = false
      this.$message.success('新增选修课表成功')
      this.search()
    },
    edit (record) {
      this.$refs.dishesEdit.setFormValues(record)
      this.dishesEdit.visiable = true
    },
    handledishesEditClose () {
      this.dishesEdit.visiable = false
    },
    handledishesEditSuccess () {
      this.dishesEdit.visiable = false
      this.$message.success('修改选修课表成功')
      this.search()
    },
    handleDeptChange (value) {
      this.queryParams.deptId = value || ''
    },
    batchDelete () {
      if (!this.selectedRowKeys.length) {
        this.$message.warning('请选择需要删除的记录')
        return
      }
      let that = this
      this.$confirm({
        title: '确定删除所选中的记录?',
        content: '当您点击确定按钮后，这些记录将会被彻底删除',
        centered: true,
        onOk () {
          let ids = that.selectedRowKeys.join(',')
          that.$delete('/cos/schedule-elective-info/' + ids).then(() => {
            that.$message.success('删除成功')
            that.selectedRowKeys = []
            that.search()
          })
        },
        onCancel () {
          that.selectedRowKeys = []
        }
      })
    },
    search () {
      let {sortedInfo, filteredInfo} = this
      let sortField, sortOrder
      // 获取当前列的排序和列的过滤规则
      if (sortedInfo) {
        sortField = sortedInfo.field
        sortOrder = sortedInfo.order
      }
      this.fetch({
        sortField: sortField,
        sortOrder: sortOrder,
        ...this.queryParams,
        ...filteredInfo
      })
    },
    reset () {
      // 取消选中
      this.selectedRowKeys = []
      // 重置分页
      this.$refs.TableInfo.pagination.current = this.pagination.defaultCurrent
      if (this.paginationInfo) {
        this.paginationInfo.current = this.pagination.defaultCurrent
        this.paginationInfo.pageSize = this.pagination.defaultPageSize
      }
      // 重置列过滤器规则
      this.filteredInfo = null
      // 重置列排序规则
      this.sortedInfo = null
      // 重置查询参数
      this.queryParams = {}
      this.fetch()
    },
    handleTableChange (pagination, filters, sorter) {
      // 将这三个参数赋值给Vue data，用于后续使用
      this.paginationInfo = pagination
      this.filteredInfo = filters
      this.sortedInfo = sorter

      this.fetch({
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...this.queryParams,
        ...filters
      })
    },
    fetch (params = {}) {
      // 显示loading
      this.loading = true
      if (this.paginationInfo) {
        // 如果分页信息不为空，则设置表格当前第几页，每页条数，并设置查询分页参数
        this.$refs.TableInfo.pagination.current = this.paginationInfo.current
        this.$refs.TableInfo.pagination.pageSize = this.paginationInfo.pageSize
        params.size = this.paginationInfo.pageSize
        params.current = this.paginationInfo.current
      } else {
        // 如果分页信息为空，则设置为默认值
        params.size = this.pagination.defaultPageSize
        params.current = this.pagination.defaultCurrent
      }
      params.staffId = this.currentUser.userId
      this.$get('/cos/schedule-elective-info/page', {
        ...params
      }).then((r) => {
        let data = r.data.data
        const pagination = {...this.pagination}
        pagination.total = data.total
        this.dataSource = data.records
        this.pagination = pagination
        // 数据加载完毕，关闭loading
        this.loading = false
      })
    }
  },
  watch: {}
}
</script>
<style lang="less" scoped>
@import "../../../../static/less/Common";
</style>
