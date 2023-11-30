// import LearnTrackDetail from "@/views/LearnTrack/LearnTrackDetail";

import LearnTrack from "@/views/LearnTrack.vue";
import LearnTrackDetail from "@/views/LearnTrack/LearnTrackDetail.vue";

export default [
  {
    path: "",
    name: "LearnTrack",
    component: LearnTrack,
  },
  {
    path: "detail/:id", // id: 记录id
    name: "TrackDetails",
    component: LearnTrackDetail,
    meta: {
      layout: "default",
    },
  },
];
