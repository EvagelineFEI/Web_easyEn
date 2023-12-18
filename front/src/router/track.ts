// import LearnTrack from "@/views/LearnTrack.vue";
import LearnTrackDetail from "@/views/LearnTrack/LearnTrackDetail.vue";
import WriteDetails from "@/views/LearnTrack/WriteDetails.vue";

export default [
  {
    path: "",
    name: "LearnTrack",
    component: LearnTrackDetail,
  },
  {
    path: "write-details",
    name: "WriteDetails",
    component: WriteDetails,
  }
];
