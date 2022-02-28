package ua.com.alevel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.model.Account;
import ua.com.alevel.model.TransactionHistory;
import ua.com.alevel.model.User;
import ua.com.alevel.repository.AccountRepository;
import ua.com.alevel.repository.TransactHistoryRepository;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/app")
public class AppController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactHistoryRepository transactHistoryRepository;

    User user;

    @GetMapping("/dashboard")
    public ModelAndView getDashboard(HttpSession session) {
        ModelAndView getDashboardPage = new ModelAndView("dashboard");

        User user = (User) session.getAttribute("user");

        List<Account> getUserAccounts = accountRepository.getUserAccountById(user.getUser_id());
        BigDecimal totalAccountsBalance = accountRepository.getTotalBalance(user.getUser_id());

        getDashboardPage.addObject("userAccounts", getUserAccounts);
        getDashboardPage.addObject("totalBalance", totalAccountsBalance);

        return getDashboardPage;
    }


    @GetMapping("/transact_history")
    public ModelAndView getTransactHistory(HttpSession httpSession) {
        ModelAndView getTransactHistoryPage = new ModelAndView("transactHistory");
        user = (User) httpSession.getAttribute("user");
        List<TransactionHistory> userTransactHistory = transactHistoryRepository.getTransactionRecordsById(user.getUser_id());
        getTransactHistoryPage.addObject("transact_history", userTransactHistory);
        return getTransactHistoryPage;
    }
}
