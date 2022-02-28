package ua.com.alevel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.alevel.model.User;
import ua.com.alevel.repository.AccountRepository;
import ua.com.alevel.repository.TransactRepository;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/transact")
public class TransactController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactRepository transactRepository;
    LocalDateTime currentDataTime = LocalDateTime.now();

    User user;
    double currentBalance;
    double newBalance;

    @PostMapping("/deposit")
    public String deposit(@RequestParam("deposit_amount") String depositAmount,
                          @RequestParam("account_id") String accountID,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {

        if (depositAmount.isEmpty() || accountID.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Deposit Amount or Account cannot be empty");
            return "redirect:/app/dashboard";
        }
        user = (User) session.getAttribute("user");

        int acc_id = Integer.parseInt(accountID);

        double depositAmountValue = Double.parseDouble(depositAmount);

        if (depositAmountValue == 0) {
            redirectAttributes.addFlashAttribute("error", "Deposit Amount cannot be 0");
            return "redirect:/app/dashboard";
        }
        currentBalance = accountRepository.getAccountBalance(user.getUser_id(), acc_id);
        newBalance = currentBalance + depositAmountValue;
        accountRepository.changeAccountBalanceById(newBalance, acc_id);

        transactRepository.logTransaction(acc_id, "deposit", depositAmountValue, "online", "success", "Deposit Transaction Successful", currentDataTime);
        redirectAttributes.addFlashAttribute("success", "Amount Deposited successfully");
        return "redirect:/app/dashboard";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam("transfer_from") String transfer_from,
                           @RequestParam("transfer_to") String transfer_to,
                           @RequestParam("transfer_amount") String transfer_amount,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        String error_msg;
        if (transfer_from.isEmpty() || transfer_to.isEmpty() || transfer_amount.isEmpty()) {
            error_msg = "The accounts transfering from and to along with the amount cannot be empty";
            redirectAttributes.addFlashAttribute("error", error_msg);
            return "redirect:/app/dashboard";
        }
        int transferFromId = Integer.parseInt(transfer_from);
        int transferToId = Integer.parseInt(transfer_to);
        double transferAmount = Double.parseDouble(transfer_amount);

        if (transferFromId == transferToId) {
            error_msg = "Cannot transfer Into the same Accoun, Please select the appropriate account to perform transfer";
            redirectAttributes.addFlashAttribute("error", error_msg);
            return "redirect:/app/dashboard";
        }

        if (transferAmount == 0) {
            error_msg = "Cannot Transfer an amount of 0, please enter value greater than 0";
            redirectAttributes.addFlashAttribute("error", error_msg);
            return "redirect:/app/dashboard";
        }

        user = (User) session.getAttribute("user");

        double currentBalanceOfAccountTransferringFrom = accountRepository.getAccountBalance(user.getUser_id(), transferFromId);

        if (currentBalanceOfAccountTransferringFrom < transferAmount) {
            error_msg = "You insufficient Founds to perform this transfer!";

            transactRepository.logTransaction(transferFromId, "Transfer", transferAmount, "online", "failed", "Insufficient Founds", currentDataTime);
            redirectAttributes.addFlashAttribute("error", error_msg);
            return "redirect:/app/dashboard";
        }

        double currentBalanceOfAccountTransferringTo = accountRepository.getAccountBalance(user.getUser_id(), transferToId);
        newBalance = transferAmount;

        double newBalanceOfAccountTransferringFrom = currentBalanceOfAccountTransferringFrom - transferAmount;
        double newBalanceOfAccountTransferringTo = currentBalanceOfAccountTransferringTo + transferAmount;

        accountRepository.changeAccountBalanceById(newBalanceOfAccountTransferringFrom, transferFromId);
        accountRepository.changeAccountBalanceById(newBalanceOfAccountTransferringTo, transferToId);

        transactRepository.logTransaction(transferFromId, "Transfer", transferAmount, "online", "success", "Transfer Transaction Successful", currentDataTime);

        String success_msg = "Transferring successfully!";
        redirectAttributes.addFlashAttribute("success", success_msg);

        return "redirect:/app/dashboard";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("withdrawal_amount") String withdrawalАmount,
                           @RequestParam("account_id") String accountID,
                           HttpSession httpSession,
                           RedirectAttributes redirectAttributes) {
        String error_msg;
        String success_msg;

        if (withdrawalАmount.isEmpty() || accountID.isEmpty()) {
            error_msg = "Withdrawal Amount and Account Withdrawing from cannot be Empty";
            redirectAttributes.addFlashAttribute("error", error_msg);
            return "redirect:/app/dashboard";
        }
        double withdrawal_amount = Double.parseDouble(withdrawalАmount);
        int account_id = Integer.parseInt(accountID);

        if (withdrawal_amount == 0) {
            error_msg = "Withdrawal cannot be of 0, please enter a value greater than 0";
            redirectAttributes.addFlashAttribute("error", error_msg);
            return "redirect:/app/dashboard";
        }
        user = (User) httpSession.getAttribute("user");
        currentBalance = accountRepository.getAccountBalance(user.getUser_id(), account_id);

        if (currentBalance < withdrawal_amount) {
            error_msg = "You insufficient Founds to perform this transfer!";

            transactRepository.logTransaction(account_id, "Withdrawal", withdrawal_amount, "online", "failed", "Insufficient Founds", currentDataTime);
            redirectAttributes.addFlashAttribute("error", error_msg);
            return "redirect:/app/dashboard";
        }

        newBalance = currentBalance - withdrawal_amount;

        accountRepository.changeAccountBalanceById(newBalance, account_id);
        transactRepository.logTransaction(account_id, "Withdrawal", withdrawal_amount, "online", "success", "Withdrawal Transaction Successful", currentDataTime);

        success_msg = "Withdrawal successful!";
        redirectAttributes.addFlashAttribute("success", success_msg);
        return "redirect:/app/dashboard";
    }
}