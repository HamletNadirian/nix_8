<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="card deposit-card">
    <div class="card-body">
        <form action="/transact/deposit" method="POST" class="deposit-form">
            <div class="form-group mb-2">
                <label for=""> Enter Deposit Amount</label>
                <input type="number" name="deposit_amount" class="form-control" placeholder="Enter Deposit Amount">
            </div>

            <div class="form-group">
                <label for="">Select Account</label>
                <select name="account_id" class="form-control" id="">
                    <option value="">-- Select Account --</option>
                    <c:if test="${userAccounts != null}">
                        <c:forEach items="${userAccounts}" var="selectAccount">
                            <option value="${selectAccount.account_id}">${selectAccount.account_name}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
            <div class="form-group my-2">
                <button id="" class="btn btn-md transact-btn">Deposit</button>
            </div>
        </form>
    </div>
</div>