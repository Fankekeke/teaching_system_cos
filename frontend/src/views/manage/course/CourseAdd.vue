<template>
  <a-modal v-model="show" title="新增课程" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="onClose">
        取消
      </a-button>
      <a-button key="submit" type="primary" :loading="loading" @click="handleSubmit">
        提交
      </a-button>
    </template>
    <a-form :form="form" layout="vertical">
      <a-row :gutter="20">
        <a-col :span="12">
          <a-form-item label='课程名称' v-bind="formItemLayout">
            <a-input v-decorator="[
            'name',
            { rules: [{ required: true, message: '请输入课程名称!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='年级' v-bind="formItemLayout">
            <a-input v-decorator="[
            'gradeId',
            { rules: [{ required: true, message: '请输入年级!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='时常' v-bind="formItemLayout">
            <a-input v-decorator="[
            'duration',
            { rules: [{ required: true, message: '请输入时常!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='上课地点' v-bind="formItemLayout">
            <a-input v-decorator="[
            'address',
            { rules: [{ required: true, message: '请输入上课地点!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='人数' v-bind="formItemLayout">
            <a-input-number :min="1" :max="999" v-decorator="[
              'peopleNum',
              { rules: [{ required: true, message: '请输入人数!' }] }
              ]" style="width: 100%"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='课程类型' v-bind="formItemLayout">
            <a-select v-decorator="[
              'type',
              { rules: [{ required: true, message: '请输入课程类型!' }] }
              ]">
              <a-select-option value="1">常规</a-select-option>
              <a-select-option value="2">选修</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :md="6" :sm="24">
          <a-form-item label='所属系' v-bind="formItemLayout">
            <a-select style="width: 100%" v-decorator="[
            'tieId',
            { rules: [{ required: true, message: '请输入所属系!' }] }
            ]">
              <a-select-option :value="item.id" v-for="(item, index) in tieList" :key="index">{{ item.name }}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :md="6" :sm="24">
          <a-form-item label='所属专业' v-bind="formItemLayout">
            <a-select style="width: 100%" v-decorator="[
            'majorId',
            { rules: [{ required: true, message: '请输入所属专业!' }] }
            ]">
              <a-select-option :value="item.id" v-for="(item, index) in majorList" :key="index">{{ item.name }}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='班级导师' v-bind="formItemLayout">
            <a-select style="width: 100%" v-decorator="[
            'staffId',
            { rules: [{ required: true, message: '请输入班级导师!' }] }
            ]" option-label-prop="label">
              <a-select-option v-for="(item, index) in staffList" :key="index" :value="item.id" :label="item.name">
                <a-row>
                  <a-col :span="2">
                    <a-avatar style="margin-right: 20px" shape="square" :size="40" icon="user" :src="'http://127.0.0.1:9527/imagesWeb/' + item.images.split(',')[0]" />
                  </a-col>
                  <a-col :span="22">
                    <a-row>
                      <a-col><span>{{item.name}}</span></a-col>
                      <a-col style="font-size: 10px">
                        {{item.deptName}} - {{item.positionName}}
                      </a-col>
                    </a-row>
                  </a-col>
                </a-row>
              </a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='课程内容' v-bind="formItemLayout">
            <a-textarea :rows="6" v-decorator="[
            'content',
             { rules: [{ required: true, message: '请输入课程内容!' }] }
            ]"/>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-modal>
</template>

<script>
import {mapState} from 'vuex'
import moment from 'moment'
function getBase64 (file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onload = () => resolve(reader.result)
    reader.onerror = error => reject(error)
  })
}
const formItemLayout = {
  labelCol: { span: 24 },
  wrapperCol: { span: 24 }
}
export default {
  name: 'dishesAdd',
  props: {
    dishesAddVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.dishesAddVisiable
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false,
      fileList: [],
      staffList: [],
      tieList: [],
      majorList: [],
      previewVisible: false,
      previewImage: ''
    }
  },
  mounted () {
    this.getStaffList()
    this.getTieList()
    this.getMajorList()
  },
  methods: {
    getTieList () {
      this.$get('/cos/tie-info/list').then((r) => {
        this.tieList = r.data.data
      })
    },
    getMajorList () {
      this.$get('/cos/major-info/list').then((r) => {
        this.majorList = r.data.data
      })
    },
    getStaffList () {
      this.$get('/cos/staff-info/queryStaffList', {enterpriseId: 13}).then((r) => {
        this.staffList = r.data.data
      })
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
    reset () {
      this.loading = false
      this.form.resetFields()
    },
    onClose () {
      this.reset()
      this.$emit('close')
    },
    handleSubmit () {
      // 获取图片List
      let images = []
      this.fileList.forEach(image => {
        images.push(image.response)
      })
      this.form.validateFields((err, values) => {
        if (values.startTime) {
          values.startTime = moment(values.startTime).format('YYYY-MM-DD HH:mm:ss')
        }
        if (values.endTime) {
          values.endTime = moment(values.endTime).format('YYYY-MM-DD HH:mm:ss')
        }
        values.staffIds = values.staffIdList.join(',')
        values.images = images.length > 0 ? images.join(',') : null
        if (!err) {
          this.loading = true
          this.$post('/cos/course-info', {
            ...values
          }).then((r) => {
            this.reset()
            this.$emit('success')
          }).catch(() => {
            this.loading = false
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
