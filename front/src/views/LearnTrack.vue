<script setup lang="ts">
import DataRangeCard from "@/components/DateRangeCard.vue"

const startDate = ref(null);
const endDate = ref(null);

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
  }
]);

// 使用 computed 定义计算属性
const filteredItems = computed(() => {
   if(startDate.value && endDate.value) {
    const sDate = new Date(startDate.value);
    const eDate = new Date(endDate.value);

    return dataItems.value.filter(item => {
      const prcDate = new Date(item.prcDate);
      return (sDate && eDate) ? prcDate >= sDate && prcDate <= eDate : true;
    });
  } else {
    return dataItems.value 
  }
});

//const formatDate = (date: Date) => {
//  if (date) {
//    const year = date.getFullYear();
//    const month = (date.getMonth() + 1).toString().padStart(2, '0');
//    const day = date.getDate().toString().padStart(2, '0');
//    return `${year}-${month}-${day}`;
//  }
//  return '';
//};

</script>


<template>
  <v-row> 
    <!-- 数据表 --> 
    <v-col>
      <v-data-table
          :items="filteredItems"
          :headers="headers"
          item-value="prcId">
        <template v-slot:item="{ item }">
          <tr>
            <td>{{ item.prcDate }}</td>
            <td>{{ item.prcDuration }} 小时</td>
            <td>{{ item.prcType }}</td>
            <td>
              <v-btn :to="{ name: 'TrackDetails', params: { id: item.prcId } }">详情</v-btn>
            </td>
          </tr>
        </template>
      </v-data-table>
    </v-col>
    
    <DataRangeCard
    v-model:start_date="startDate"
    v-model:end_date="endDate">
    </DataRangeCard>
    
  </v-row>
</template>


<style scoped>

</style>