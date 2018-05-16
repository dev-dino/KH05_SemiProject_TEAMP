package semi.teamP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import semi.teamP.dto.BoardDTO;
import semi.teamP.dto.PlanDTO;

public class PlanDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public PlanDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	private void resClose() {
		try {
			if(rs != null) {
				rs.close();
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	/*public Integer write(String userName, String sDate, String eDate, String memo) {
		int success = 0;
		plan_idx NUMBER(20) PRIMARY KEY,
		   plan_endDay DATE NOT NULL,
		   plan_content NVARCHAR2(100),
		   plan_startDay DATE NOT NULL,
		   plan_state NVARCHAR2(50) DEFAULT '준비중',
		   group_idx NUMBER(20),
		   member_id NVARCHAR2(50)
		String sql = "INSERT INTO plan(plan_idx,member_id,plan_startDay,plan_endDay,plan_content)"
				+"VALUES(plan_idx_seq,?,?,?,?)";
		try {
			ps= conn.prepareStatement(sql,new String[] {"plan_idx"} );
			ps.setString(1, userName);
			ps.setString(2, sDate);
			ps.setString(3, eDate);
			ps.setString(4, memo);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				success = (int) rs.getLong(1);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		
		return success;
	}*/





	 public int planWrite(PlanDTO plandto) {
	      int success = 0;
	      String sql = "INSERT INTO Plan(plan_idx, member_id,plan_startDay,plan_endDay,plan_content,plan_title,group_idx)"
	            + " VALUES(plan_idx_seq.NEXTVAL,?,?,?,?,?,?)";
	      //
	      try {
	         ps = conn.prepareStatement(sql, new String[] {"plan_idx"});
	         ps.setString(1, plandto.getMember_id());
	         //   ps.setString(1, infoDto.getGroup_name());
	         ps.setString(2, plandto.getPlan_startDay());
	         ps.setString(3, plandto.getPlan_endDay());
	         ps.setString(4,plandto.getPlan_content());
	         ps.setString(5,plandto.getPlan_title());
	         ps.setInt(6, plandto.getGroup_idx());
	         success = ps.executeUpdate();
	         
	      }catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         resClose();
	      }
	      return success;
	      
	   
	   }



	public ArrayList<PlanDTO> list(int groupIdx) {
		GroupDAO Gdao = new GroupDAO();
		ArrayList<PlanDTO> list = new ArrayList<PlanDTO>();
		String sql="SELECT * FROM plan WHERE group_idx=? ORDER BY plan_startDay DESC";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, groupIdx);
			rs = ps.executeQuery();
			while(rs.next()) {
				PlanDTO dto = new PlanDTO();
				dto.setPlan_idx(rs.getInt("plan_idx"));
				dto.setMember_id(rs.getString("member_id"));
				dto.setPlan_state(rs.getString("plan_state"));
				dto.setPlan_startDay(Gdao.castingString(rs.getDate("plan_startDay")));
				dto.setPlan_endDay(Gdao.castingString(rs.getDate("plan_endDay")));
				dto.setPlan_title(rs.getString("plan_title"));
				dto.setPlan_content(rs.getString("plan_content"));
				dto.setPlan_plan_finishDate(Gdao.castingString(rs.getDate("plan_finishDate")));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			resClose();
		}
		return list;
		
	}
}

