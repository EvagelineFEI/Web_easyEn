<script setup lang="ts">
const props = defineProps([
  'title', 'content'
])

const showPassage = ref(false);
</script>

<template>
  <v-dialog
    v-model="showPassage"
    max-width="500px"
  >
    <template v-slot:activator="{ props }">
      <v-btn
        color="info"
        variant="text"
        v-bind="props"
      >
        查看详情
      </v-btn>
    </template>
    <v-card>
      <v-card-text>
        <v-form>
          <v-row>
            <v-col cols="12" sm="12">
              <v-text-field
                  label="标题"
                  :model-value="props.title"
                  readonly
              >
              </v-text-field>
              <v-textarea
                  rows="12"
                  label="正文"
                  :model-value="props.content"
                  readonly
              >
              </v-textarea>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-btn color="primary" @click="showPassage = !showPassage">关闭</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>