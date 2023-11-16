<template>
  <v-sheet elevation="0">
    <v-row justify="center">
      <v-col cols="5" class="align-self-center">
        <v-text-field
            v-model="startDate"
            type="date"
            variant="outlined"
            :label="$t('track.start')">
        </v-text-field>
      </v-col>
      <v-col cols="5" class="align-self-center">
        <v-text-field
            v-model="endDate"
            type="date"
            variant="outlined"
            :label="$t('track.end')">
        </v-text-field>
      </v-col>
      <v-row class="justify-end">
        <v-col class="justify-center">
          <v-card-actions>
            <v-btn @click="updateDateRange" icon="mdi-arrow-top-right" color="primary"></v-btn>
            <v-btn @click="cleanDateRange" icon="mdi-close" color="primary"></v-btn>
          </v-card-actions>
        </v-col>
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