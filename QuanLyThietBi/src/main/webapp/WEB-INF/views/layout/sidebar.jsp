<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
	<div class="menu_section">
		<h3>General</h3>
			<ul class="nav side-menu" id="home">
				<li><a href='<c:url value="/index"></c:url>'><i class="fa fa-home"></i>Trang chủ  </a>					
				</li>
				
			</ul>
			<c:if test="${userInfo.getListRole().contains('ROLE_INVENTORY')}"> 
				<ul class="nav side-menu" id="inventory">
					<li><a><i class="	fa fa-industry"></i>Kho   <span class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu">
							<li id="productstocklist"><a href='<c:url value="/product-stock/list"></c:url>'>Tồn kho </a></li>
							<li id="stockchecklist"><a href='<c:url value="/stock-check/list"></c:url>'>Kiểm kê </a></li>											
							<li id="goodsissuelist"><a href='<c:url value="/goods-issue/list"></c:url>'>Danh sách xuất kho</a></li>	
							<li id="goodsreceiptlist"><a href='<c:url value="/goods-receipt/list"></c:url>'>Danh sách nhập kho</a></li>							
						</ul>
					</li>
				</ul>
			</c:if>
			<c:if test="${userInfo.getListRole().contains('ROLE_EMPLOYEE')}"> 
				<ul class="nav side-menu" id="maintenance">
					<li><a><i class="fa fa-tasks"></i>Bảo Trì <span class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu">
							<li id="maintenanceaction"><a href='<c:url value="/maintenance/add"></c:url>'>Phiếu bảo trì</a></li>
							<li id="maintenancelist"><a href='<c:url value="/maintenance/list"></c:url>'>Danh sách bảo trì</a></li>						
						</ul>
					</li>
				</ul>
			</c:if>
			 <c:if test="${userInfo.getListRole().contains('ROLE_ADMIN')}"> 
				<ul class="nav side-menu" id="manage">
					<li><a><i class="fa fa-users"></i>Quản lý<span class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu">
							<li id="inventorylist"><a href='<c:url value="/inventory/list"></c:url>'>Kho</a></li>
							<li id="userlist"><a href='<c:url value="/user/list"></c:url>'>Tài khoản</a></li>	
							<li id="productlist"><a href='<c:url value="/product/list"></c:url>'>Sản phẩm</a></li>
							<li id="customerslist"><a href='<c:url value="/customers/list"></c:url>'>Khách hàng</a></li>
							<li id="supplierlist"><a href='<c:url value="/supplier/list"></c:url>'>Nhà cung cấp</a></li>
							<li id="categorylist"><a href='<c:url value="/category/list"></c:url>'>Danh mục</a></li>	
							<li id="brandlist"><a href='<c:url value="/brand/list"></c:url>'>Hãng sản xuất</a></li>				
						</ul>
					</li>
				</ul>
			 </c:if>
			  <c:if test="${userInfo.getListRole().contains('ROLE_ADMIN')}"> 
			<ul class="nav side-menu" id="statistics">
					<li><a><i class="fa fa-bar-chart" aria-hidden="true"></i>Báo cáo - Thống kê <span class="fa fa-chevron-down"></span></a>
						<ul class="nav child_menu">
							<li id="statistics-maintenance"><a href='<c:url value="/statistics/maintenance/1"></c:url>'>Bảo trì</a></li>
							<li id="statistics-goodsreceipt"><a href='<c:url value="/statistics/goods-receipt/1"></c:url>'>Nhập hàng</a></li>
							<li id="statistics-goodsissue"><a href='<c:url value="/statistics/goods-issue/1"></c:url>'>Xuất hàng</a></li>
							<li id="chart"><a href='<c:url value="/statistics/chart"></c:url>'>Chart</a></li>												
						</ul>
					</li>
			</ul>
			 </c:if> 	
	</div>
</div>