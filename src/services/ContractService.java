package services;

import entities.Contract;

public class ContractService implements OnlinePaymentService{

    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, int months) {

        for (int i = 1; i <= months; i++) {
            double amount = contract.getTotalValue() / months; // valor da parcela
            amount += onlinePaymentService.interest(amount, i); // juros
            amount += onlinePaymentService.paymentFee(amount); // taxa de pagamento

            contract.addInstallment(amount, i); // adiciona a parcela ao contrato
        }
    }

    @Override
    public double paymentFee(double amount) {
        return 0;
    }

    @Override
    public double interest(double amount, int months) {
        return 0;
    }
}