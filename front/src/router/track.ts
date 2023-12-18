// import LearnTrack from "@/views/LearnTrack.vue";
import LearnTrackDetail from "@/views/LearnTrack/LearnTrackDetail.vue";

export default [
  {
    path: "",
    name: "LearnTrack",
    component: LearnTrackDetail,
  },
  {
    path: "detail/:id", // id: userid
    name: "TrackDetails",
    component: LearnTrackDetail,
  },
];
