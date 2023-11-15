<template>
    <v-card rounded height="280px" width="25%">
      <v-col>
        <v-card-title>
          时间范围
        </v-card-title>
        <v-text-field
        v-model="startDate"
        type="date"
        :label="$t('track.start')">
        </v-text-field>
        <v-text-field
        v-model="endDate"
        type="date"
        :label="$t('track.end')">
        </v-text-field>
        <v-card-actions>
          <v-btn @click="updateDateRange" color="primary" >确定</v-btn>
          <v-btn @click="cleanDateRange" color="primary" >取消</v-btn>
        </v-card-actions>
      </v-col>
    </v-card>
</template>

<script setup lang="ts">


const props = defineProps({
    start_date: String,
    end_date: String
})

const emit = defineEmits(['update:start_date', "update:end_date"])

const startDate = ref(null);
const endDate = ref(null);

function cleanDateRange() {
    startDate.value = null
    endDate.value = null
    emit('update:start_date', null)
    emit('update:end_date', null)
}

function updateDateRange() {
  emit('update:start_date', startDate.value)
  emit('update:end_date', endDate.value)
}

</script>