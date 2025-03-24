<template>
  <a-modal v-model="show" title="修改课程" @cancel="onClose" :width="800">
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
                  <a-col :span="4">
                    <a-avatar style="margin-right: 20px" shape="square" :size="40" icon="user" :src="'http://127.0.0.1:9527/imagesWeb/' + item.images.split(',')[0]" />
                  </a-col>
                  <a-col :span="20">
                    <a-row>
                      <a-col><span>{{item.name}}</span></a-col>
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
      let fields = ['name', 'staffId', 'gradeId', 'duration', 'address', 'peopleNum', 'type', 'tieId', 'majorId', 'content']
      let obj = {}
      Object.keys(dishes).forEach((key) => {
        if (key === 'images') {
          this.fileList = []
          this.imagesInit(dishes['images'])
        }
        if (key === 'startTime') {
          dishes[key] = moment(dishes[key])
        }
        if (key === 'endTime') {
          dishes[key] = moment(dishes[key])
        }
        if (key === 'staffIds') {
          setTimeout(() => {
            dishes['staffIdList'] = dishes[key].split(',').map(Number)
            // this.staffIds = dishes[key].split(',')
            console.log(dishes['staffIdList'])
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
          values.startTime = moment(values.startTime).format('YYYY-MM-DD HH:mm:ss')
        }
        if (values.endTime) {
          values.endTime = moment(values.endTime).format('YYYY-MM-DD HH:mm:ss')
        }
        // values.staffIds = values.staffIdList.join(',')
        if (!err) {
          this.loading = true
          this.$put('/cos/course-info', {
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
