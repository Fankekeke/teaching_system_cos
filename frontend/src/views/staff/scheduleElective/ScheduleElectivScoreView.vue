<template>
  <a-modal v-model="show" title="成绩填写" @cancel="onClose" :width="1000">
    <template slot="footer">
      <a-button key="back" @click="onClose" type="danger">
        关闭
      </a-button>
      <a-button key="back1" @click="saveScore" type="primary">
        提交
      </a-button>
    </template>
    <div style="font-size: 13px;font-family: SimHei" v-if="dishesData !== null">
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">基础信息</span></a-col>
        <a-col :span="8"><b>课程时间：</b>
          {{ dishesData.courseDate }}
        </a-col>
        <a-col :span="8"><b>课程名称：</b>
          {{ dishesData.courseName ? dishesData.courseName : '- -' }}
        </a-col>
        <a-col :span="8"><b>代课老师：</b>
          {{ dishesData.staffName ? dishesData.staffName : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>开始时间：</b>
          {{ dishesData.startTime }}
        </a-col>
        <a-col :span="8"><b>结束时间：</b>
          {{ dishesData.endTime }}
        </a-col>
        <a-col :span="8"><b>时常：</b>
          {{ dishesData.duration }}
        </a-col>
      </a-row>
      <br/>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>人数：</b>
          {{ dishesData.peopleNum }}
        </a-col>
        <a-col :span="8"><b>学分：</b>
          {{ dishesData.credit }}
        </a-col>
        <a-col :span="8"><b>专业名称：</b>
          {{ dishesData.majorName }}
        </a-col>
      </a-row>
      <br/>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;" v-if="dataList.length !== 0">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">班级分数</span></a-col>
        <a-col style="margin-bottom: 15px">
          <a-alert message="必须填写规范" banner />
          <a-table :columns="columns" :data-source="dataList" :pagination="false">
            <template slot="nameShow" slot-scope="text, record">
              <span>{{ record.name }}</span>
            </template>
            <template slot="classNameShow" slot-scope="text, record">
              <span>{{ record.className }}</span>
            </template>
            <template slot="majorNameShow" slot-scope="text, record">
              <span>{{ record.majorName }}</span>
            </template>
            <template slot="phoneShow" slot-scope="text, record">
              <span>{{ record.phone }}</span>
            </template>
            <template slot="scoreShow" slot-scope="text, record">
              <a-input-number v-model="record.score" :min="0" :step="1"/>
            </template>
          </a-table>
        </a-col>
      </a-row>
    </div>
  </a-modal>
</template>

<script>
import moment from 'moment'
moment.locale('zh-cn')
function getBase64 (file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onload = () => resolve(reader.result)
    reader.onerror = error => reject(error)
  })
}
export default {
  name: 'dishesView',
  props: {
    dishesShow: {
      type: Boolean,
      default: false
    },
    dishesData: {
      type: Object
    }
  },
  computed: {
    show: {
      get: function () {
        return this.dishesShow
      },
      set: function () {
      }
    },
    columns () {
      return [{
        title: '学生姓名',
        dataIndex: 'name',
        scopedSlots: {customRender: 'nameShow'}
      }, {
        title: '头像',
        dataIndex: 'images',
        customRender: (text, record, index) => {
          if (!record.images) return <a-avatar shape="square" icon="user" />
          return <a-popover>
            <template slot="content">
              <a-avatar shape="square" size={132} icon="user" src={ 'http://127.0.0.1:9527/imagesWeb/' + record.images.split(',')[0] } />
            </template>
            <a-avatar shape="square" icon="user" src={ 'http://127.0.0.1:9527/imagesWeb/' + record.images.split(',')[0] } />
          </a-popover>
        }
      }, {
        title: '所属班级',
        dataIndex: 'className',
        scopedSlots: {customRender: 'classNameShow'}
      }, {
        title: '所属专业',
        dataIndex: 'majorName',
        scopedSlots: {customRender: 'majorNameShow'}
      }, {
        title: '联系方式',
        dataIndex: 'phone',
        scopedSlots: {customRender: 'phoneShow'}
      }, {
        title: '分数',
        dataIndex: 'score',
        scopedSlots: {customRender: 'scoreShow'}
      }]
    }
  },
  data () {
    return {
      loading: false,
      fileList: [],
      previewVisible: false,
      previewImage: '',
      repairInfo: null,
      reserveInfo: null,
      dataList: [],
      staffList: [],
      logisticsList: [],
      userInfo: null
    }
  },
  watch: {
    dishesShow: function (value) {
      if (value) {
        this.queryStaffListByCondition(this.dishesData.id)
      }
    }
  },
  methods: {
    queryStaffListByCondition (id) {
      this.$get('/cos/schedule-elective-info/queryScheduleElectiveStudentList', {id: id}).then((r) => {
        this.dataList = r.data.data
      })
    },
    local (dishesData) {
      baiduMap.clearOverlays()
      baiduMap.rMap().enableScrollWheelZoom(true)
      // eslint-disable-next-line no-undef
      let point = new BMap.Point(dishesData.longitude, dishesData.latitude)
      baiduMap.pointAdd(point)
      baiduMap.findPoint(point, 16)
      // let driving = new BMap.DrivingRoute(baiduMap.rMap(), {renderOptions:{map: baiduMap.rMap(), autoViewport: true}});
      // driving.search(new BMap.Point(this.nowPoint.lng,this.nowPoint.lat), new BMap.Point(scenic.point.split(",")[0],scenic.point.split(",")[1]));
    },
    imagesInit (images) {
      if (images !== null && images !== '') {
        let imageList = []
        images.split(',').forEach((image, index) => {
          imageList.push({uid: index, name: image, status: 'done', url: 'http://127.0.0.1:9527/imagesWeb/' + image})
        })
        this.fileList = imageList
      }
    },
    handleCancel () {
      this.previewVisible = false
    },
    async handlePreview (file) {
      if (!file.url && !file.preview) {
        file.preview = await getBase64(file.originFileObj)
      }
      this.previewImage = file.url || file.preview
      this.previewVisible = true
    },
    picHandleChange ({ fileList }) {
      this.fileList = fileList
    },
    saveScore () {
      if (this.dataList.length === 0) {
        this.$message.error('请填写学生信息')
        return false
      }
      this.dataList.forEach((item, index) => {
        item.studentId = item.id
        item.courseId = this.dishesData.courseId
        item.electiveId = this.dishesData.id
      })
      this.$post('/cos/elective-score-record/batchSaveScore', {
        courseId: this.dishesData.courseId,
        electiveId: this.dishesData.id,
        scoreDataStr: JSON.stringify(this.dataList)
      }).then((r) => {
        this.$emit('success')
      })
    },
    onClose () {
      this.$emit('close')
    }
  }
}
</script>

<style scoped>

</style>
