<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ attribute name="prefix" required="true" type="java.lang.String" %>

<!-- LINK FOR NAV -->
<%@ attribute name="link1" type="java.lang.String" %>
<%@ attribute name="link2" type="java.lang.String" %>
<%@ attribute name="link3" type="java.lang.String" %>
<%@ attribute name="link4" type="java.lang.String" %>
<%@ attribute name="link5" type="java.lang.String" %>
<%@ attribute name="link6" type="java.lang.String" %>
<%@ attribute name="link7" type="java.lang.String" %>
<%@ attribute name="link8" type="java.lang.String" %>

<!-- URI FOR NAV -->
<%@ attribute name="uri1" type="java.lang.String" %>
<%@ attribute name="uri2" type="java.lang.String" %>
<%@ attribute name="uri3" type="java.lang.String" %>
<%@ attribute name="uri4" type="java.lang.String" %>
<%@ attribute name="uri5" type="java.lang.String" %>
<%@ attribute name="uri6" type="java.lang.String" %>
<%@ attribute name="uri7" type="java.lang.String" %>
<%@ attribute name="uri8" type="java.lang.String" %>

<!-- ITEM NAME FOR NAV -->
<%@ attribute name="name1" type="java.lang.String" %>
<%@ attribute name="name2" type="java.lang.String" %>
<%@ attribute name="name3" type="java.lang.String" %>
<%@ attribute name="name4" type="java.lang.String" %>
<%@ attribute name="name5" type="java.lang.String" %>
<%@ attribute name="name6" type="java.lang.String" %>
<%@ attribute name="name7" type="java.lang.String" %>
<%@ attribute name="name8" type="java.lang.String" %>

<%-- ICON FOR NAV--%>
<%@ attribute name="icon1" type="java.lang.String" %>
<%@ attribute name="icon2" type="java.lang.String" %>
<%@ attribute name="icon3" type="java.lang.String" %>
<%@ attribute name="icon4" type="java.lang.String" %>
<%@ attribute name="icon5" type="java.lang.String" %>
<%@ attribute name="icon6" type="java.lang.String" %>
<%@ attribute name="icon7" type="java.lang.String" %>
<%@ attribute name="icon8" type="java.lang.String" %>

<ul class="list">
    <li>
        <a class="list__item" href="#">
            <span class="icon">
                <ion-icon name="logo-microsoft"></ion-icon>
            </span>
				<!-- <ion-icon name="logo-apple"></ion-icon> -->
<%--				<img src="<c:url value="imgs/brand-name.png"/>" width="60px"/>--%>
            <span class="title">Branch name</span>
        </a>
    </li>
    <li>
        <a class="list__item" data-uri="dashboard" href="${prefix}/index.htm">
                <span class="icon"> <ion-icon name="home-outline"></ion-icon>
                </span> <span class="title">Dash board</span>
        </a>
    </li>
    <c:if test="${uri1 != null}">
        <li>
            <a class="list__item" data-uri="${uri1}" href="${prefix}/${link1}">
            <span class="icon"> <ion-icon name="${icon1}"></ion-icon>
            </span> <span class="title">${name1}</span>
            </a>
        </li>
    </c:if>
    <c:if test="${uri2 != null}">
        <li>
            <a class="list__item" data-uri="${uri2}" href="${link2}">
			<span class="icon"> <ion-icon name="${icon2}"></ion-icon>
            </span> <span class="title">${name2}</span>
            </a>
        </li>
    </c:if>
    <c:if test="${uri3 != null}">
        <li>
            <a class="list__item" data-uri="${uri3}" href="${link3}">
                <span class="icon"> <ion-icon name="${icon3}"></ion-icon>
                </span> <span class="title">${name3}</span>
            </a>
        </li>
    </c:if>
    <c:if test="${uri4 != null}">
        <li>
            <a class="list__item" data-uri="${uri4}" href="${link4}">
				<span class="icon"> <ion-icon name="${icon4}"></ion-icon>
                </span> <span class="title">${name4}</span>
            </a>
        </li>
    </c:if>
    <c:if test="${uri5 != null}">
        <li>
            <a class="list__item" data-uri="${uri5}" href="${link5}">
				<span class="icon"> <ion-icon name="${icon5}"></ion-icon>
                </span> <span class="title">${name5}</span>
            </a>
        </li>
    </c:if>
    <c:if test="${uri6 != null}">
        <li>
            <a class="list__item" data-uri="${uri6}" href="${link6}">
				<span class="icon"> <ion-icon name="${icon6}"></ion-icon>
                </span> <span class="title">${name6}</span>
            </a>
        </li>
    </c:if>
    <c:if test="${uri7 != null}">
        <li>
            <a class="list__item" data-uri="${uri7}" href="${link7}">
				<span class="icon"> <ion-icon name="${icon7}"></ion-icon>
                </span> <span class="title">${name7}</span>
            </a>
        </li>
    </c:if>
    <c:if test="${uri8 != null}">
        <li>
            <a class="list__item" data-uri="${uri8}" href="${prefix}/${link8}">
				<span class="icon"> <ion-icon name="${icon8}"></ion-icon>
                </span> <span class="title">${name8}</span>
            </a>
        </li>
    </c:if>
</ul>
