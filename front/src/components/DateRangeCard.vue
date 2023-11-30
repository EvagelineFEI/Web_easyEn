<template>
  <v-sheet elevation="0">
    <v-row justify="center" align="center">

      <v-col cols="5" align-self="center" >
        <v-text-field
            v-model="startDate"
            type="date"
            variant="outlined"
            hide-details
            :label="$t('track.start')">
        </v-text-field>
      </v-col>

      <v-col cols="5" align-self="center" >
        <v-text-field
            v-model="endDate"
            type="date"
            variant="outlined"
            hide-details
            :label="$t('track.end')">
        </v-text-field>
      </v-col>


      <v-row justify="center">
        <v-tooltip
            v-model="showFilter"
            location="top">
          <template v-slot:activator="{ props }">
            <v-btn 
              v-bind="props" 
              variant="plain" 
              @click="updateDateRange" 
              icon="mdi-arrow-top-right" 
              color="primary"
            />
          </template>
          <span>筛选</span>
        </v-tooltip>
        <v-tooltip
            v-model="cleanFilter"
            location="top">
          <template v-slot:activator="{ props }">
            <v-btn 
              v-bind="props" 
              variant="plain" 
              @click="cleanDateRange" 
              icon="mdi-close" 
              color="primary"
            />
          </template>
          <span>清空</span>
        </v-tooltip>
      </v-row>
    </v-row>
  </v-sheet>
</template>

<script setup lang="ts">
defineProps({
  start_date: String,
  end_date: String
})

const emit = defineEmits(['update:start_date', "update:end_date"])

const startDate = ref(null as String | null);
const endDate = ref(null as String | null);
const showFilter = ref(false);
const cleanFilter = ref(false);

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