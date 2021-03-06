package work1;

import java.util.Scanner;
public class Bankchoose {
    private static Customer account = null;

    public static int menu() {
        int choose = 0;
        System.out.println("欢迎使用银行系统");
        System.out.println("1.开户");
        System.out.println("2.存款");
        System.out.println("3.取款");
        System.out.println("4.消费");
        System.out.println("5.还款");
        System.out.println("6.银行结算");
        System.out.println("7.查询余额");
        System.out.println("8.退出");
        System.out.println("请选择（1-8）: ");
        Scanner scan = new Scanner(System.in);
        choose = scan.nextInt();
        return choose;
    }

    public static int menu2() {
        int choose = 0;
        System.out.println("请选择开卡类型");
        System.out.println("1.信用卡checkingAccount");
        System.out.println("2.存储卡savingAccount");
        System.out.println("3.返回exit");
        System.out.println("请选择（1-3）：");
        Scanner scan = new Scanner(System.in);
        choose = scan.nextInt();
        return choose;
    }

    public static void createCheckingAccount() {
        System.out.print("请输入SSN: ");
        Scanner scan1 = new Scanner(System.in);
        String ssn = scan1.nextLine();
        System.out.print("请输入姓名: ");
        Scanner scan2 = new Scanner(System.in);
        String name = scan2.nextLine();
        System.out.print("请输入卡号: ");
        Scanner scan3 = new Scanner(System.in);
        String accountNum = scan3.nextLine();
        System.out.print("请输入服务费率: ");
        Scanner scan4 = new Scanner(System.in);
        double serviceCharge = scan4.nextDouble();
        CheckingAccount cAccount = new CheckingAccount(accountNum, 0, serviceCharge);
        if (account == null) {
            account = new Customer(ssn, name, cAccount);
        } else {
            account.setSsn(ssn);
            account.setName(name);
            account.setCheckingAccount(cAccount);
        }
        System.out.println("创建成功！");
    }

    public static void createSavingsAccount() {
        System.out.print("请输入SSN: ");
        Scanner scan1 = new Scanner(System.in);
        String ssn = scan1.nextLine();
        System.out.print("请输入姓名: ");
        Scanner scan2 = new Scanner(System.in);
        String name = scan2.nextLine();
        System.out.print("请输入卡号: ");
        Scanner scan3 = new Scanner(System.in);
        String accountNum = scan3.nextLine();
        System.out.print("请输入利率: ");
        Scanner scan4 = new Scanner(System.in);
        double interestRate = scan4.nextDouble();
        SavingsAccount sAccount = new SavingsAccount(accountNum, 0, interestRate);
        if (account == null) {
            account = new Customer(ssn, name, sAccount);
        } else {
            account.setSsn(ssn);
            account.setName(name);
            account.setSavingsAccount(sAccount);
        }

        System.out.println("创建成功！");
    }

    public static void save() {
        System.out.print("输入存款金额：");
        Scanner scan = new Scanner(System.in);
        double money = scan.nextDouble();
        if (money < 0) {
            System.out.println("输入错误！");
            return;
        } else {
            account.getSavingsAccount().setBalance(account.getSavingsAccount().getBalance() + money);
            System.out.println("存入成功！");
        }
    }

    public static void withdraw() {
        System.out.print("输入取款金额：");
        Scanner scan = new Scanner(System.in);
        double money = scan.nextDouble();
        if (money > account.getSavingsAccount().getBalance() || money < 0) {
            System.out.println("余额不足或输入错误！");
            return;
        } else {
            account.getSavingsAccount().setBalance(account.getSavingsAccount().getBalance() - money);
            System.out.println("取款成功！");
        }
    }

    public static void comsume() {
        System.out.print("输入消费金额：");
        Scanner scan = new Scanner(System.in);
        double money = scan.nextDouble();
        if (money < 0) {
            System.out.println("输入错误！");
            return;
        } else {
            account.getCheckingAccount().setBalance(account.getCheckingAccount().getBalance() + money);
            System.out.println("支付成功！");
        }
    }

    public static void repay() {
        System.out.print("输入还款金额：");
        Scanner scan = new Scanner(System.in);
        double money = scan.nextDouble();
        if (money > account.getCheckingAccount().getBalance() || money < 0) {
            System.out.println("大于欠款金额或输入不正确！");
            return;
        } else {
            account.getCheckingAccount().setBalance(account.getCheckingAccount().getBalance() - money);
            System.out.println("还款成功！");
        }
    }

    public static void settle() {
        double money = account.getCheckingAccount().getServiceCharge() * account.getCheckingAccount().getBalance();
        System.out.println("服务费：" + money);
        account.getCheckingAccount().assessServiceCharge();
        double money2 = account.getSavingsAccount().getInterestRate() * account.getSavingsAccount().getBalance();
        System.out.println("利息：" + money2);
        account.getSavingsAccount().payInterest();
    }

    public static void balance() {
        System.out.println("信用卡待还：" + account.getCheckingAccount().getBalance());
        System.out.println("存储卡余额：" + account.getSavingsAccount().getBalance());
    }
}

