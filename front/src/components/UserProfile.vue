<script lang="ts" setup>
import configs from "@/configs";
import index from "@/router";
import router from "@/router";
import auth from "@/api/auth";

const authStore = configs.stores.useAuthStore()
const handleLogout = () => {
  router.push("/");
  setTimeout(()=>{
    authStore.logout();
  }, 100)
};

const enableProfile = ref(false);
const enableAdvice = ref(false);
const editing = ref(false);
const loading = ref(false);

const userName = ref('');
const email = ref('');
const userContent = ref('');
const advice = ref('反馈邮箱： 2244069890@qq.com');

const profileTrigger = () => {
    enableProfile.value = !enableProfile.value;
}

const adviceTrigger = () => {
  enableAdvice.value = !enableAdvice.value;
}

const toggleEdit = () => {
  editing.value = !editing.value;
}

const getUserInfo = async () => {
  const data = {
    user_id: authStore.user_id
  }
  await auth.getUserInfo(authStore.user, data)
    .then((response) => {
      if (response.code === 200) {
        userName.value = response.resultData.username;
        email.value = response.resultData.email;
        userContent.value = response.resultData.other_info;
      }
    })
}


const saveChanges = async () => {
  const data = {
    name: userName.value,
  }

  loading.value = !loading.value;

  await auth.updateUserInfo(authStore.user as string, data)
    .then((response) => {
      if (response.code === 200) {
        editing.value = !editing.value;
    }
    })

  loading.value = !loading.value;
  editing.value = !editing.value;
}

const navs = [
  {
    title: "用户资料",
    key: "menu.profileDetails",
    icon: "mdi-account-box-outline",
  },
]
</script>

<template>
  <v-menu
      :close-on-content-click="false"
      location="bottom right"
      transition="slide-y-transition"
  >
    <template v-slot:activator="{ props }">
      <v-btn class="mx-2" icon v-bind="props" @click="getUserInfo">
        <v-badge bordered color="success" content="2" dot>
          <v-avatar color="info" size="40">
            <v-icon icon="mdi-account-circle"></v-icon>
          </v-avatar>
        </v-badge>
      </v-btn>
    </template>

    <v-card max-width="300">
      <v-list density="compact" lines="three">
        <!-- ---------------------------------------------- -->
        <!-- Profile Area -->
        <!-- ---------------------------------------------- -->
        <v-list-item>
          <template v-slot:prepend>
            <v-avatar color="info" size="40">
              <v-icon icon="mdi-account-circle"></v-icon>
            </v-avatar>
          </template>

          <v-list-item-title class="font-weight-bold text-primary">
            {{ userName ? userName : 'User' }}
          </v-list-item-title>
          <v-list-item-subtitle>
             {{ email ? email : 'Email' }}
          </v-list-item-subtitle>
        </v-list-item>
      </v-list>
      <v-divider/>
      <!-- ---------------------------------------------- -->
      <!-- Menu Area -->
      <!-- ---------------------------------------------- -->

      <v-list :lines="false" density="compact" elevation="0" variant="flat">
        <v-list-item
            v-for="(nav, i) in navs"
            :key="i"
            color="primary"
            @click="profileTrigger"
            density="compact"
            link
        >
          <template v-slot:prepend>
            <v-avatar size="30">
              <v-icon>{{ nav.icon }}</v-icon>
            </v-avatar>
          </template>

          <div>
            <v-list-item-subtitle class="text-body-2">
            {{ nav.title }}
            </v-list-item-subtitle>
          </div>
        </v-list-item>
      </v-list>
      <v-divider/>
      <!-- ---------------------------------------------- -->
      <!-- Logout Area -->
      <!-- ---------------------------------------------- -->
      <v-list :lines="false" density="compact" elevation="0" variant="flat">
        <v-list-item color="primary" @click="adviceTrigger" density="compact" link>
          <template v-slot:prepend>
            <v-avatar size="30">
              <v-icon>mdi-lifebuoy</v-icon>
            </v-avatar>
          </template>

          <div>
            <v-list-item-subtitle class="text-body-2">
              意见反馈
            </v-list-item-subtitle>
          </div>
        </v-list-item>
        <v-list-item
            color="primary"
            density="compact"
            link
            @click="handleLogout"
        >
          <template v-slot:prepend>
            <v-avatar size="30">
              <v-icon>mdi-logout</v-icon>
            </v-avatar>
          </template>

          <div>
            <v-list-item-subtitle class="text-body-2">
              登出
            </v-list-item-subtitle>
          </div>
        </v-list-item>
      </v-list>
    </v-card>
  </v-menu>

  <!-- profile -->
  <v-dialog v-model="enableProfile" max-width="600px">
    <v-card>
      <v-card-title>
        <span class="headline">{{ editing ? '编辑用户资料' : '用户资料' }}</span>
      </v-card-title>
      <v-card-text>
        <v-form>
          <v-row>
            <v-col cols="12" sm="12">
              <v-row justify="center">
                <v-avatar size="80" color="primary">
                  <v-icon class="align-self-center" icon="mdi-account-circle" size="60"></v-icon>
                </v-avatar>
              </v-row>
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field
                  v-model="userName"
                  label="姓名"
                  :readonly="!editing.valueOf()"
                  :loading="loading">
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="6">
              <v-text-field
                  v-model="email"
                  label="邮箱"
                  readonly
                  :loading="loading">
              </v-text-field>
            </v-col>
            <v-col cols="12" sm="12">
              <v-textarea
                  v-model="userContent"
                  label="用户简介"
                  readonly
                  :loading="loading">
              </v-textarea>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-btn color="primary" @click="toggleEdit" v-if="!editing">编辑</v-btn>
        <v-btn color="primary" @click="saveChanges" v-if="editing">保存</v-btn>
        <v-btn color="primary" @click="profileTrigger">关闭</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>

  <!-- advice -->
  <v-dialog v-model="enableAdvice" max-width="600px">
    <v-card>
      <v-card-title>
        <span class="headline">意见反馈</span>
      </v-card-title>
      <v-card-text>
        <v-form>
          <v-row>
            <v-col cols="12" sm="12">
              <v-textarea
                  v-model="advice"
                  readonly
              >
              </v-textarea>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-btn color="primary" @click="adviceTrigger">关闭</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>

</template>