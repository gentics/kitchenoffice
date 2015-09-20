<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<jsp:include page="include/header.jsp" />

<jsp:include page="include/navigation.jsp" />
<div class="container">
	<div class="content">

		<div class="wrapper">
			<div class="proper-content">
				<flash-messages></flash-messages>
				<div class="container" data-ng-view></div>
			</div>
			<!-- /.proper-content -->

			<div class="push"></div>

		</div>
		<!-- /.wrapper -->

		<div class="footer-wrapper">
			<div class="container">
				<p>
					<i class="icon-info"></i> Project Version: ${project.version} | <i class="icon-github"></i> <a target="_blank" href="${github.url}"> GitHub Project</a> | <i class="icon-bug"></i> Found a bug? Got ideas? Please report here: <a target="_blank" href="${project.issueManagement.url}">${project.issueManagement.system}</a>
				</p>
			</div>
		</div>
	</div>
</div>

<div class="github-fork-ribbon-wrapper right-bottom">
	<div class="github-fork-ribbon">
		<a href="${github.url}" target="_blank">Fork me on GitHub</a>
	</div>
</div>

<jsp:include page="include/footer.jsp" />