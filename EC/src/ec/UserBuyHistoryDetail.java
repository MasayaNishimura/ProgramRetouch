package ec;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import beans.BuyDetailDataBeans;
import beans.ItemDataBeans;
import dao.BuyDAO;
import dao.BuyDetailDAO;

/**
 * 購入履歴画面
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserBuyHistoryDetail")
public class UserBuyHistoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		ArrayList<BuyDetailDataBeans> detailArrayList = new ArrayList<BuyDetailDataBeans>();
		BuyDataBeans bdb2 = new BuyDataBeans();
		BuyDetailDAO bdd1 = new BuyDetailDAO();
		BuyDetailDAO bdd2 = new BuyDetailDAO();
		ArrayList<ItemDataBeans> idbArrayList = new ArrayList<ItemDataBeans>();


		try {
			detailArrayList = bdd1.getBuyDataDetailsById(id);
			idbArrayList = bdd2.getItemDataById(detailArrayList.get(0).getBuyId());
			bdb2 = BuyDAO.getBuyDataBeansByBuyId(id);
			request.setAttribute("detailArrayList", detailArrayList);
			request.setAttribute("idbArrayList", idbArrayList);
			request.setAttribute("bdb2", bdb2);
			request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}






	}
}
