<template>
  <p>
    <a-space>
      <a-button type="primary" @click="handleQuery()">刷新</a-button>
      <a-button type="primary" @click="onAdd">新增</a-button>
    </a-space>
  </p>
  <a-table
    :dataSource="programmers"
    :columns="columns"
    :pagination="pagination"
    @change="handleTableChange"
    :loading="loading"
  >
    <template #bodyCell="{ column, record }">

      <template v-if="column.dataIndex === 'homepage'">
        <a :href="record[column.dataIndex]" target="_blank">{{
          record[column.dataIndex]
        }}</a>
      </template>
      <template v-if="column.dataIndex === 'avatar'">
        <a-avatar :src="record[column.dataIndex]" />
      </template>
      <template v-if="column.dataIndex === 'operation'">
        <a-space>
          <a-popconfirm
            title="删除后不可恢复，确认删除?"
            @confirm="onDelete(record)"
            ok-text="确认"
            cancel-text="取消"
          >
            <a style="color: red">删除</a>
          </a-popconfirm>
          <a @click="onEdit(record)">编辑</a>
        </a-space>
      </template>
    </template>
  </a-table>
  <a-modal
    v-model:visible="visible"
    title=""
    @ok="handleOk"
    ok-text="确认"
    cancel-text="取消"
  >
    <a-form
      :model="programmer"
      :label-col="{ span: 4 }"
      :wrapper-col="{ span: 20 }"
    >
      <a-form-item label="账号">
        <a-input v-model:value="programmer.account" />
      </a-form-item>
      <a-form-item label="手机号">
        <a-input v-model:value="programmer.mobile" />
      </a-form-item>
      <a-form-item label="昵称">
        <a-input v-model:value="programmer.nickname" />
      </a-form-item>
      <a-form-item label="头像">
        <a-input v-model:value="programmer.avatar" />
      </a-form-item>
      <a-form-item label="描述">
        <a-input v-model:value="programmer.description" />
      </a-form-item>
      <a-form-item label="个人主页">
        <a-input v-model:value="programmer.homepage" />
      </a-form-item>
      <a-form-item label="技能">
        <a-input v-model:value="programmer.skill" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { notification } from "ant-design-vue";
import axios from "axios";

const visible = ref(false);
let programmer = ref({
  id: undefined,
  account: undefined,
  mobile: undefined,
  nickname: undefined,
  avatar: undefined,
  description: undefined,
  homepage: undefined,
  skill: undefined,
  createTime: undefined,
  updateTime: undefined,
});
const programmers = ref([]);
// 分页的三个属性名是固定的
const pagination = ref({
  total: 0,
  current: 1,
  pageSize: 10,
});
let loading = ref(false);
const columns = [
  {
    title: "账号",
    dataIndex: "account",
    key: "account",
  },
  {
    title: "手机号",
    dataIndex: "mobile",
    key: "mobile",
  },
  {
    title: "昵称",
    dataIndex: "nickname",
    key: "nickname",
  },
  {
    title: "头像",
    dataIndex: "avatar",
    key: "avatar",
  },
  {
    title: "描述",
    dataIndex: "description",
    key: "description",
  },
  {
    title: "个人主页",
    dataIndex: "homepage",
    key: "homepage",
  },
  {
    title: "技能",
    dataIndex: "skill",
    key: "skill",
  },
  {
    title: "操作",
    dataIndex: "operation",
  },
];

const onAdd = () => {
  programmer.value = {};
  visible.value = true;
};

const onEdit = (record) => {
  programmer.value = window.Tool.copy(record);
  visible.value = true;
};

const onDelete = (record) => {
  axios
    .delete("/business/admin/programmer/delete/" + record.id)
    .then((response) => {
      const data = response.data;
      if (data.success) {
        notification.success({ description: "删除成功！" });
        handleQuery({
          page: pagination.value.current,
          size: pagination.value.pageSize,
        });
      } else {
        notification.error({ description: data.message });
      }
    });
};

const handleOk = () => {
  axios
    .post("/business/admin/programmer/save", programmer.value)
    .then((response) => {
      let data = response.data;
      if (data.success) {
        notification.success({ description: "保存成功！" });
        visible.value = false;
        handleQuery({
          page: pagination.value.current,
          size: pagination.value.pageSize,
        });
      } else {
        notification.error({ description: data.message });
      }
    });
};

const handleQuery = (param) => {
  if (!param) {
    param = {
      page: 1,
      size: pagination.value.pageSize,
    };
  }
  loading.value = true;
  axios
    .get("/business/admin/programmer/query-list", {
      params: {
        page: param.page,
        size: param.size,
      },
    })
    .then((response) => {
      loading.value = false;
      let data = response.data;
      if (data.success) {
        programmers.value = data.content.list;
        // 设置分页控件的值
        pagination.value.current = param.page;
        pagination.value.total = data.content.total;
      } else {
        notification.error({ description: data.message });
      }
    });
};

const handleTableChange = (pagination) => {
  handleQuery({
    page: pagination.current,
    size: pagination.pageSize,
  });
};

onMounted(() => {
  handleQuery({
    page: 1,
    size: pagination.value.pageSize,
  });
});
</script>
