<template>
  <v-sheet elevation="0">
    <v-row align="center" justify="center">

      <v-col align-self="center" cols="5">
        <v-text-field
            v-model="startDate"
            :label="$t('track.start')"
            hide-details
            type="date"
            variant="outlined"
        />
      </v-col>

      <v-col align-self="center" cols="5">
        <v-text-field
            v-model="endDate"
            :label="$t('track.end')"
            hide-details
            type="date"
            variant="outlined"
        />
      </v-col>

      <v-row justify="center">
        <v-tooltip
            v-model="showFilter"
            location="top">
          <template v-slot:activator="{ props }">
            <v-btn
                color="primary"
                icon="mdi-arrow-top-right"
                v-bind="props"
                variant="plain"
                @click="updateDateRange"
            />
          </template>
          <span>筛选</span>
        </v-tooltip>
        <v-tooltip
            v-model="cleanFilter"
            location="top">
          <template v-slot:activator="{ props }">
            <v-btn
                color="primary"
                icon="mdi-close"
                v-bind="props"
                variant="plain"
                @click="cleanDateRange"
            />
          </template>
          <span>清空</span>
        </v-tooltip>
      </v-row>
    </v-row>
  </v-sheet>
</template>

<script lang="ts" setup>
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