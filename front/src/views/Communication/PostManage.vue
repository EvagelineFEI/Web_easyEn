<script setup lang="ts">
import DataRangeCard from "@/components/DateRangeCard.vue"
import communicate, {CommentData, PostComment} from "@/api/communication";
import {onMounted, ref, Ref} from "vue";
import type {UserPostData, PostPages} from "@/api/communication";
import {useAuthStore} from "@/configs/stores/authStore";
import practice, {WriteData} from "@/api/practice";
// import {onMounted, ref} from "vue/dist/vue";

const startDate = ref(null as string | null);
const endDate = ref(null as string | null);
const user_comment = ref<CommentData[]>([]);
const headers = [
  {title: 'Title', align: 'center', key: 'postTitle'},
  {title: 'Date', align: 'end', key: 'postDate'},
  {title: 'Date-line', align: 'end', key: 'prcDuration'},
  {title: '', align: 'end', sortable: false},
];

const auth = useAuthStore();
const recvPosts: Ref<PostComment[]> = ref([]);
const loading = ref(false);
const snackProvider = ref(false);
const snackProviderMessages = ref("");
const snackColor = ref("success");
const overlay = ref(false);

const snackHandler = (message: string, color: string) => {
  snackProviderMessages.value = message;
  snackColor.value = color;
  snackProvider.value = true;
};

async function loadItems() {
  await communicate.showPostByUser(Number(auth.user_id), {page: 1, pageSize: 10})
      .then((response) => {
        if (response.code === 200) {
          console.log(response)
          recvPosts.value = response.resultData as PostComment[];
        } else {
          recvPosts.value = [] as PostComment[];
        }
      })
}
// 使用 computed 定义计算属性
const filteredItems = computed(() => {
  return recvPosts.value.filter(post => {
    return (!startDate.value || new Date(post.posts.time as string) >= new Date(startDate.value)) &&
    (!endDate.value || new Date(post.posts.time as string) <= new Date(endDate.value));
  })
});
const deletePost = async (post_id: number) => {
  const data: UserPostData = {
    post_id,
    user_id: auth.user_id,
    title: '',
    content: '',
    public_or: false
  }
  overlay.value = true;

  await communicate.deletePost(auth.user as string,post_id, data)
      .then((response) => {
        console.log(response);
        if (response.code === 200) {
          snackHandler("删除成功", "success");
          loadItems();
        } else {
          snackHandler("加载失败", "error");
        }
      });

  overlay.value = false;
};
loadItems();
</script>


<template>
  <v-row>
    <!-- 数据表 -->
    <v-col>
      <v-btn icon @click="$router.go(-1)">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <v-row justify="center">
        <v-col cols="10">
          <DataRangeCard
              v-model:start_date="startDate"
              v-model:end_date="endDate">
          </DataRangeCard>
        </v-col>
      </v-row>
      <v-data-table
          :items="filteredItems"
          :headers="headers"
          item-value="postId">
        <template v-slot:item="{ item }">
          <tr>
            <td class="text-center">{{ item.postDate }}</td>
            <td class="text-end">{{ item.postDate }}</td>
            <td class="text-end">{{ item.postDate }}</td>
            <td>
            <v-row justify="end">
                <v-col cols="2"> 
                    <v-btn color="primary" :to="{ name: 'Write', params: { id: item.postId } }">编辑</v-btn>

                </v-col>
                <v-col cols="2"> 
                    <v-btn color="primary" :to="{ name: 'Post', params: { id: item.postId } }">查看</v-btn>
                </v-col>
                <v-col cols="2"> 
                    <v-btn color="error" :to="{ name: 'Post', params: { id: item.postId } }">删除</v-btn>
                </v-col>
              <v-col cols="4">
                <v-dialog
                    max-width="500px"
                >
                  <template v-slot:activator="{ props }">
                    <v-btn
                        variant="text"
                        icon="mdi-delete"
                        color="error"
                        v-bind="props"
                    />
                  </template>
                  <v-card>
                    <v-card-title>
                      删除文章
                    </v-card-title>
                    <v-card-subtitle>
                      点击方框外取消
                    </v-card-subtitle>
                    <v-card-text>
                      是否要删除文章？
                    </v-card-text>
                    <v-card-actions>
                      <v-spacer/>
                      <v-btn
                          color="primary"
                          @click="deletePost(Number(item.essay_id))"
                      >
                        确定
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-col>
            </v-row>
            </td>
          </tr>
        </template>
      </v-data-table>
    </v-col>
  </v-row>
</template>


<style scoped>

</style>