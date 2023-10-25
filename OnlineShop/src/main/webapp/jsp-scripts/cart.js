function calculateTotal() {
    let singlePrices = document.getElementsByClassName('mb-0 singlePrice');
    let productCounts = document.getElementsByClassName('form-control form-control-sm productCount');
    let totalElement = document.getElementById('total');
    let total = 0;
    for (let i = 0; i < singlePrices.length; ++i) {
        total = total + parseFloat(singlePrices[i].innerHTML) * parseInt(productCounts[i].value, 10);
    }
    totalElement.innerHTML = total.toString(10) + '$';
}

let selectAddress = document.getElementById('addressSelect');
let addressDiv = document.getElementById('addressDiv');
let addressH5 = document.getElementById('addressH5');
let shippingCost = document.getElementById('shippingCost');
let addressInput = document.getElementById('addressInput');
selectAddress.onchange = function () {
    if (selectAddress.value === "Self pickup") {
        addressDiv.style.display = "none";
        addressH5.style.display = "none";
        shippingCost.value = 0;
        addressInput.reset();
    } else {
        addressDiv.style.display = "block";
        addressH5.style.display = "block";
        shippingCost.value = 10;
    }
}

function cc_format(ccid) {

    let ccNumString = document.getElementById(ccid).value;
    ccNumString = ccNumString.replace(/[^0-9]/g, '');
    // mc, starts with - 51 to 55
    // v, starts with - 4
    // dsc, starts with 6011, 622126-622925, 644-649, 65
    // amex, starts with 34 or 37
    let typeCheck = ccNumString.substring(0, 2);
    let cType = '';
    let block1;
    let block2 = '';
    let block3 = '';
    let block4 = '';
    let formatted;

    if (typeCheck.length === 2) {
        typeCheck = parseInt(typeCheck);
        if (typeCheck >= 40 && typeCheck <= 49) {
            cType = 'Visa';
        } else if (typeCheck >= 51 && typeCheck <= 55) {
            cType = 'Master Card';
        } else if ((typeCheck >= 60 && typeCheck <= 62) || (typeCheck === 64) || (typeCheck === 65)) {
            cType = 'Discover';
        } else if (typeCheck === 34 || typeCheck === 37) {
            cType = 'American Express';
        } else {
            cType = 'Invalid';
        }
    }

    // all support card types have a 4 digit firt block
    block1 = ccNumString.substring(0, 4);
    if (block1.length === 4) {
        block1 = block1 + ' ';
    }

    if (cType === 'Visa' || cType === 'Master Card' || cType === 'Discover') {
        // for 4X4 cards
        block2 = ccNumString.substring(4, 8);
        if (block2.length === 4) {
            block2 = block2 + ' ';
        }
        block3 = ccNumString.substring(8, 12);
        if (block3.length === 4) {
            block3 = block3 + ' ';
        }
        block4 = ccNumString.substring(12, 16);
    } else if (cType === 'American Express') {
        // for Amex cards
        block2 = ccNumString.substring(4, 10);
        if (block2.length === 6) {
            block2 = block2 + ' ';
        }
        block3 = ccNumString.substring(10, 15);
        block4 = '';
    } else if (cType === 'Invalid') {
        // for Amex cards
        block1 = typeCheck;
        block2 = '';
        block3 = '';
        block4 = '';
        alert('Invalid Card Number');
    }

    formatted = block1 + block2 + block3 + block4;
    document.getElementById(ccid).value = formatted;
}

function copyCode() {
    document.getElementById('checkCodeInput').value = document.getElementById('inputCode').value;
}

function validateForm() {
    let creditCardNumber = document.getElementById('creditCardNumber');
    let creditCardYear = document.getElementById('creditCardYear');
    let creditCardCVV = document.getElementById('creditCardCVV');
    if (creditCardNumber.value === '') {
        creditCardNumber.style.border = 'solid';
        creditCardNumber.style.borderColor = '#FF0000';
        return false;
    } else {
        creditCardNumber.style.border = 'none';
        creditCardNumber.style.borderColor = 'transparent';
    }
    if (creditCardYear.value === '') {
        creditCardYear.style.border = 'solid';
        creditCardYear.style.borderColor = '#FF0000';
        return false;
    } else {
        creditCardYear.style.border = 'none';
        creditCardYear.style.borderColor = 'transparent';
    }
    if (creditCardCVV.value === '') {
        creditCardCVV.style.border = 'solid';
        creditCardCVV.style.borderColor = '#FF0000';
        return false;
    } else {
        creditCardCVV.style.border = 'none';
        creditCardCVV.style.borderColor = 'transparent';
    }

}