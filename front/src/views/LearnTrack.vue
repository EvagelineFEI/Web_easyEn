<script setup lang="ts">
import DataRangeCard from "@/components/DateRangeCard.vue"

const startDate = ref(null as string | null);
const endDate = ref(null as string | null);

const headers = [
  {title: 'Date', align: 'start', key: 'prcDate'},
  {title: 'Duration', align: 'start', key: 'prcDuration'},
  {title: 'Type', align: 'start', key: 'prcType'},
  {title: '', sortable: false},
];

const dataItems = ref([
  {
    prcId: '1',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    prcDate: '2023-11-01',
    prcDuration: '3.5'      //练习时长
  },
  {
    prcId: '2',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    prcDate: '2023-11-06',
    prcDuration: '3.5'      //练习时长
  },
  {
    prcId: '3',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    prcDate: '2023-11-02',
    prcDuration: '3.5'      //练习时长
  },
  {
    prcId: '4',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    prcDate: '2023-11-03',
    prcDuration: '3.5'      //练习时长
  },

  {
    prcId: '5',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    prcDate: '2023-11-01',
    prcDuration: '3.5'      //练习时长
  },
  {
    prcId: '6',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    prcDate: '2023-11-06',
    prcDuration: '3.5'      //练习时长
  },
  {
    prcId: '7',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    prcDate: '2023-11-02',
    prcDuration: '3.5'      //练习时长
  },
  {
    prcId: '8',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    prcDate: '2023-11-03',
    prcDuration: '3.5'      //练习时长
  },

  {
    prcId: '9',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    prcDate: '2023-11-01',
    prcDuration: '3.5'      //练习时长
  },
  {
    prcId: '10',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    prcDate: '2023-11-06',
    prcDuration: '3.5'      //练习时长
  },
  {
    prcId: '11',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    prcDate: '2023-11-02',
    prcDuration: '3.5'      //练习时长
  },
  {
    prcId: '12',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    prcDate: '2023-11-03',
    prcDuration: '3.5'      //练习时长
  },

  {
    prcId: '13',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    prcDate: '2023-11-01',
    prcDuration: '3.5'      //练习时长
  },
  {
    prcId: '14',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    prcDate: '2023-11-06',
    prcDuration: '3.5'      //练习时长
  },
  {
    prcId: '15',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    prcDate: '2023-11-02',
    prcDuration: '3.5'      //练习时长
  },
  {
    prcId: '16',
    userId: 'xxxxx',
    prcType: 'PracticeType',
    prcDate: '2023-11-03',
    prcDuration: '3.5'      //练习时长
  },
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
    <!-- 数据表 -->
      <v-row justify="center">
        <v-col cols="10">
          <DataRangeCard
              v-model:start_date="startDate"
              v-model:end_date="endDate">
          </DataRangeCard>
        </v-col>
      </v-row>
      <v-data-table
          class="h-full"
          :items="filteredItems"
          :headers="headers"
          item-value="prcId">
        <template v-slot:item="{ item }">
          <tr>
            <td>{{ item.prcDate }}</td>
            <td>{{ item.prcDuration }} 小时</td>
            <td>{{ item.prcType }}</td>
            <td>
              <v-row justify="center">
                 <v-btn color="primary" :to="{ name: 'TrackDetails', params: { id: item.prcId } }">详情</v-btn>
              </v-row>
            </td>
          </tr>
        </template>
      </v-data-table>
</template>


<style scoped>

</style>