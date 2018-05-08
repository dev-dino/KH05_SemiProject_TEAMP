package semi.teamP.dto;

import java.sql.Date;

public class PlanDTO {
   private int plan_idx;
   private Date plan_startDay;
   private Date plan_endDay;
   private String plan_content;
   private String plan_state;
   private int group_idx;
   private String member_id;
   public int getPlan_idx() {
      return plan_idx;
   }
   public void setPlan_idx(int plan_idx) {
      this.plan_idx = plan_idx;
   }
   public Date getPlan_startDay() {
      return plan_startDay;
   }
   public void setPlan_startDay(Date plan_startDay) {
      this.plan_startDay = plan_startDay;
   }
   public Date getPlan_endDay() {
      return plan_endDay;
   }
   public void setPlan_endDay(Date plan_endDay) {
      this.plan_endDay = plan_endDay;
   }
   public String getPlan_content() {
      return plan_content;
   }
   public void setPlan_content(String plan_content) {
      this.plan_content = plan_content;
   }
   public String getPlan_state() {
      return plan_state;
   }
   public void setPlan_state(String plan_state) {
      this.plan_state = plan_state;
   }
   public int getGroup_idx() {
      return group_idx;
   }
   public void setGroup_idx(int group_idx) {
      this.group_idx = group_idx;
   }
   public String getMember_id() {
      return member_id;
   }
   public void setMember_id(String member_id) {
      this.member_id = member_id;
   }


}