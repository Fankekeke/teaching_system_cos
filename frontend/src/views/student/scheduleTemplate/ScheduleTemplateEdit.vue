<template>
  <a-modal v-model="show" title="修改课表模板" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="onClose">
        取消
      </a-button>
      <a-button key="submit" type="primary" :loading="loading" @click="handleSubmit">
        修改
      </a-button>
    </template>
    <a-form :form="form" layout="vertical">
      <a-row :gutter="20">
        <a-col :span="12">
          <a-form-item label='课程周' v-bind="formItemLayout">
            <a-select v-decorator="[
              'typeDay',
              { rules: [{ required: true, message: '请输入课表模板特性!' }] }
              ]">
              <a-select-option value="1">周日</a-select-option>
              <a-select-option value="2">周一</a-select-option>
              <a-select-option value="3">周二</a-select-option>
              <a-select-option value="4">周三</a-select-option>
              <a-select-option value="5">周四</a-select-option>
              <a-select-option value="6">周五</a-select-option>
              <a-select-option value="7">周六</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='课程开始时间' v-bind="formItemLayout">
            <a-time-picker format="HH:mm:ss" style="width: 100%" v-decorator="[
            'startTime',
            { rules: [{ required: true, message: '请输入课程开始时间!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='课程结束时间' v-bind="formItemLayout">
            <a-time-picker format="HH:mm:ss" style="width: 100%" v-decorator="[
            'endTime',
            { rules: [{ required: true, message: '请输入课程结束时间!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='所属班级' v-bind="formItemLayout">
            <a-select style="width: 100%" v-decorator="[
            'classId',
            { rules: [{ required: true, message: '请输入所属班级!' }] }
            ]">
              <a-select-option :value="item.id" v-for="(item, index) in classList" :key="index">{{ item.name }}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='选择课程' v-bind="formItemLayout">
            <a-select style="width: 100%" v-decorator="[
            'courseId',
            { rules: [{ required: true, message: '请选择课程!' }] }
            ]">
              <a-select-option :value="item.id" v-for="(item, index) in courseList" :key="index">{{ item.name }}</a-select-option>
            </a-select>
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
  name: 'dishesEdit',
  props: {
    dishesEditVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.dishesEditVisiable
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      rowId: null,
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false,
      staffIds: [],
      staffList: [],
      fileList: [],
      classList: [],
      courseList: [],
      previewVisible: false,
      previewImage: ''
    }
  },
  mounted () {
    this.getStaffList()
    this.getClassList()
    this.getCourseList()
  },
  methods: {
    getClassList () {
      this.$get('/cos/class-info/list').then((r) => {
        this.classList = r.data.data
      })
    },
    getCourseList () {
      this.$get('/cos/course-info/list').then((r) => {
        this.courseList = r.data.data
      })
    },
    getStaffList () {
      this.$get('/cos/staff-info/list').then((r) => {
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
    imagesInit (images) {
      if (images !== null && images !== '') {
        let imageList = []
        images.split(',').forEach((image, index) => {
          imageList.push({uid: index, name: image, status: 'done', url: 'http://127.0.0.1:9527/imagesWeb/' + image})
        })
        this.fileList = imageList
      }
    },
    setFormValues ({...dishes}) {
      this.rowId = dishes.id
      let fields = ['typeDay', 'content', 'classId', 'startTime', 'endTime', 'courseId']
      let obj = {}
      Object.keys(dishes).forEach((key) => {
        if (key === 'images') {
          this.fileList = []
          this.imagesInit(dishes['images'])
        }
        if (key === 'startTime') {
          dishes[key] = moment(dishes[key], 'HH:mm:ss')
        }
        if (key === 'endTime') {
          dishes[key] = moment(dishes[key], 'HH:mm:ss')
        }
        if (key === 'staffIds') {
          setTimeout(() => {
            dishes['staffIdList'] = dishes[key].split(',').map(Number)
            // this.staffIds = dishes[key].split(',')
          }, 500)
        }
        if (fields.indexOf(key) !== -1) {
          this.form.getFieldDecorator(key)
          obj[key] = dishes[key]
        }
      })
      this.form.setFieldsValue(obj)
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
        if (image.response !== undefined) {
          images.push(image.response)
        } else {
          images.push(image.name)
        }
      })
      this.form.validateFields((err, values) => {
        values.id = this.rowId
        values.images = images.length > 0 ? images.join(',') : null
        if (values.startTime) {
          values.startTime = moment(values.startTime).format('HH:mm:ss')
        }
        if (values.endTime) {
          values.endTime = moment(values.endTime).format('HH:mm:ss')
        }
        // values.staffIds = values.staffIdList.join(',')
        if (!err) {
          this.loading = true
          this.$put('/cos/schedule-template-info', {
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
