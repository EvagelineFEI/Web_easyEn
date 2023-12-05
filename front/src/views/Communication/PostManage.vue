<script setup lang="ts">
import DataRangeCard from "@/components/DateRangeCard.vue"

const startDate = ref(null as string | null);
const endDate = ref(null as string | null);

const headers = [
  {title: 'Title', align: 'center', key: 'postTitle'},
  {title: 'Date', align: 'end', key: 'postDate'},
  {title: 'Date-line', align: 'end', key: 'prcDuration'},
  {title: '', align: 'end', sortable: false},
];

const dataItems = ref([
  {
    postId: '1',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    postDate: '2023-11-01',
    prcDuration: '3.5'      //练习时长
  },
  {
    postId: '2',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    postDate: '2023-11-06',
    postDuration: '3.5'      //练习时长
  },
  {
    postId: '3',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    postDate: '2023-11-02',
    prcDuration: '3.5'      //练习时长
  },
  {
    postId: '4',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    postDate: '2023-11-03',
    prcDuration: '3.5'      //练习时长
  }
]);

// 使用 computed 定义计算属性
const filteredItems = computed(() => {
  return dataItems.value.filter(item => {
    const prcDate = new Date(item.prcDate);
    return (!startDate.value || prcDate >= new Date(startDate.value)) &&
        (!endDate.value || prcDate <= new Date(endDate.value));
  });
});

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