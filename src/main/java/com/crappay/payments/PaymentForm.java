/* 
  </style>
  <form id="form-checkout" action="/process_payment" method="POST">
    <div id="form-checkout__cardNumber" class="container"></div>
    <div id="form-checkout__expirationDate" class="container"></div>
    <div id="form-checkout__securityCode" class="container"></div>
    <input type="text" id="form-checkout__cardholderName" placeholder="Titular de la tarjeta" />
    <select id="form-checkout__issuer" name="issuer">
      <option value="" disabled selected>Banco emisor</option>
    </select>
    <select id="form-checkout__installments" name="installments">
      <option value="" disabled selected>Cuotas</option>
    </select>
    <select id="form-checkout__identificationType" name="identificationType">
      <option value="" disabled selected>Tipo de documento</option>
    </select>
    <input type="text" id="form-checkout__identificationNumber" name="identificationNumber" placeholder="Número do documento" />
    <input type="email" id="form-checkout__email" name="email" placeholder="E-mail" />

    <input id="token" name="token" type="hidden">
    <input id="paymentMethodId" name="paymentMethodId" type="hidden">
    <input id="transactionAmount" name="transactionAmount" type="hidden" value="100">
    <input id="description" name="description" type="hidden" value="Nome do Produto">

    <button type="submit" id="form-checkout__submit">Pagar</button>
  </form>
*/

package com.crappay.payments;

public class PaymentForm {
    private String cardNumber;
    private String expirationDate;
    private String securityCode;
    private String cardholderName;
    private String issuer;
    private String installments;
    private String identificationType;
    private String identificationNumber;
    private String email;

    // Getters and setters for all fields
}