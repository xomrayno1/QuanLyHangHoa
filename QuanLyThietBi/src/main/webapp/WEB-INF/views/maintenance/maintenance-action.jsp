<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	<div class="right_col" role="main">
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>${title}</h3>
						</div>
					</div>
		<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_content">
									<br />
									<form:form servletRelativeAction="/maintenance/save"  modelAttribute="submitForm" method="POST" cssClass="form-horizontal form-label-left">
										<form:hidden path="id"/>
										<form:hidden path="activeFlag"/>
										<form:hidden path="createDate"/>
										<form:hidden path="updateDate"/>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Tên người phụ trách <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">												
												<form:input path="name" cssClass="form-control" readonly="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="name" cssClass="help-block"/>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="idProduct">Sản phẩm<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<c:choose>
													<c:when test="${!viewOnly}">
														<form:select path="idProduct" cssClass="form-control">
															<form:option value="0">Chọn sản phẩm</form:option>
															<c:forEach items="${listProduct}" var="item">
																<form:option value="${item.id}">${item.name}  - ${item.code}</form:option>
															</c:forEach>
														</form:select>
													</c:when>
													<c:otherwise>
														<form:input path="productDTO.name" cssClass="form-control" readonly="${viewOnly}"/>
													</c:otherwise>
												</c:choose>
												<div class="has-error">
													<form:errors path="idProduct" cssClass="help-block"/>
												</div>						
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="description">Mô tả<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:textarea path="description" cssClass="form-control" readonly="${viewOnly}"/>
												
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="description">Tình trạng<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<c:choose>
													<c:when test="${!viewOnly }">
														<form:select path="status" cssClass="form-control">
															<form:option value="1">Đang xử lý</form:option>
															<form:option value="2">Đã hoàn thành</form:option>
														</form:select>
													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when test="${status == 1 }">
																<input value="Đang xử lý" class="form-control" readonly="readonly">
															</c:when>
															<c:otherwise>
																<input value="Đã hoàn thành" class="form-control" readonly="readonly">
															</c:otherwise>
														</c:choose>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="ln_solid"></div>
											<div class="item form-group">
												<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<c:if test="${!viewOnly}">
													<button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-ok-circle"></i> Submit</button>
													<button class="btn btn-primary" type="reset"><i class="glyphicon glyphicon-refresh"></i> Reset</button>	
												</c:if>			
												<a href='<c:url value="/maintenance/list/1"></c:url>'><button class="btn btn-primary" type="button"><i class="glyphicon glyphicon-minus-sign"></i> Cancel</button></a>																					
												</div>
											</div>
									</form:form>
								</div>
							</div>
						</div>
					</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#maintenanceaction').addClass('current-page').siblings().removeClass("current-page");
			$("#maintenanceaction").parents("li").addClass("active").siblings().removeClass("active");
			$("#maintenanceaction").parents().show();			
		});	
	</script>