<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="card transfer-card">
    <div class="card-body">

        <form action="/transact/transfer" method="POST">
            <div class="form-group">
                <label for="">Select Account</label>
                <select name="transfer_from" class="form-control" id="">
                    <option value="">-- Select Account --</option>
                    <c:if test="${userAccounts != null}">
                        <c:forEach items="${userAccounts}" var="selectAccount">
                            <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>

            <div class="form-group">
                <label for="">Select Account</label>
                <select name="transfer_to" class="form-control" id="">
                    <option value="">-- Select Account --</option>
                    <c:if test="${userAccounts != null}">
                        <c:forEach items="${userAccounts}" var="selectAccount">
                            <option value="${selectAccount.account_id}">${selectAccount.account_name}
                                (${selectAccount.account_type})
                            </option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>

            <div class="form-group mb-2">
                <label for=""> Enter transfer Amount</label>
                <input type="number" name="transfer_amount" class="form-control" placeholder="Enter transfer Amount">
            </div>
            <div class="form-group my-2">
                <button id="" class="btn btn-md transact-btn">Transfer</button>
            </div>
        </form>
    </div>
</div>