<template>
  <a-modal v-model="show" title="班级详情" @cancel="onClose" :width="1000">
    <template slot="footer">
      <a-button key="back" @click="onClose" type="danger">
        关闭
      </a-button>
    </template>
    <div style="font-size: 13px;font-family: SimHei" v-if="dishesData !== null">
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">基础信息</span></a-col>
        <a-col :span="8"><b>班级编号：</b>
          {{ dishesData.code }}
        </a-col>
        <a-col :span="8"><b>班级名称：</b>
          {{ dishesData.name ? dishesData.name : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>年级：</b>
          {{ dishesData.gradeYaer }}
        </a-col>
        <a-col :span="8"><b>创建时间：</b>
          {{ dishesData.createDate }}
        </a-col>
        <a-col :span="8"><b>代课老师：</b>
          {{ dishesData.staffName }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>所属系：</b>
          {{ dishesData.tieName }}
        </a-col>
        <a-col :span="8"><b>所属专业：</b>
          {{ dishesData.majorName }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="24"><b>班级内容：</b>
          {{ dishesData.content }}
        </a-col>
      </a-row>
      <br/>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">老师头像</span></a-col>
        <a-col :span="24">
          <a-upload
            name="avatar"
            action="http://127.0.0.1:9527/file/fileUpload/"
            list-type="picture-card"
            :file-list="fileList"
            @preview="handlePreview"
            @change="picHandleChange"
          >
          </a-upload>
          <a-modal :visible="previewVisible" :footer="null" @cancel="handleCancel">
            <img alt="example" style="width: 100%" :src="previewImage" />
          </a-modal>
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;" v-if="studentInfo.length !== 0">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">班级成员</span></a-col>
        <a-row :gutter="15">
          <a-col :span="6" v-for="(item, index) in studentInfo" :key="index">
            <a-card :bordered="false">
              <a-card-meta :title="item.name" :description="item.phone">
                <a-avatar
                  slot="avatar"
                  :src="'http://127.0.0.1:9527/imagesWeb/' + item.images.split(',')[0]"
                />
              </a-card-meta>
            </a-card>
          </a-col>
        </a-row>
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
      durgList: [],
      studentInfo: [],
      staffList: [],
      logisticsList: [],
      userInfo: null
    }
  },
  watch: {
    dishesShow: function (value) {
      if (value) {
        this.imagesInit(this.dishesData.staffImages)
        this.queryDetail(this.dishesData.id)
      }
    }
  },
  methods: {
    queryDetail (id) {
      this.$get(`/cos/class-info/detail/${id}`).then((r) => {
        this.studentInfo = r.data.studentInfo
      })
    },
    queryStaffListByCondition (id) {
      this.$get('/cos/class-info/queryStaffListByCondition', {conditionId: id}).then((r) => {
        this.staffList = r.data.data
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
    onClose () {
      this.$emit('close')
    }
  }
}
</script>

<style scoped>

</style>
