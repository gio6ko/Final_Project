var product_total_amt = document.getElementById('product_total_amt');
var total_cart_amt = document.getElementById('total_cart_amt');
const decreaseNumber = (id, price) => {
    var textBox = document.getElementById('textbox' + id);
    var itemValue = document.getElementById('itemval' + id);
    console.log(itemValue.innerHTML);
// console.log(textBox.value);
    if (textBox.value <= 0) {
        textBox.value = 0;
        alert('Negative quantity not allowed');
    } else {
        textBox.value = parseInt(textBox.value) - 1;
        textBox.style.background = '#fff';
        textBox.style.color = '#000';
        itemValue.innerHTML = parseInt(itemValue.innerHTML) - price;
        product_total_amt.innerHTML = parseInt(product_total_amt.innerHTML) - price;
        total_cart_amt.innerHTML = parseInt(product_total_amt.innerHTML);
    }
}
const increaseNumber = (id, price) => {
    var textBox = document.getElementById('textbox' + id);
    var itemValue = document.getElementById('itemval' + id);
// console.log(textBox.value);
    if (textBox.value >= 5) {
        textBox.value = 5;
        alert('max 5 allowed');
        textBox.style.background = 'red';
        textBox.style.color = '#fff';
    } else {
        textBox.value = parseInt(textBox.value) + 1;
        itemValue.innerHTML = parseInt(itemValue.innerHTML) + price;
        product_total_amt.innerHTML = parseInt(product_total_amt.innerHTML) + price;
        total_cart_amt.innerHTML = parseInt(product_total_amt.innerHTML);
    }
}


const addToHiddenInput = () => {
    let hiddenValue = document.getElementById('hiddenValue')

    hiddenValue.value = parseInt(total_cart_amt.innerText)

    let values = Array.from(document.querySelectorAll('.priceValue'))


    let zeroValue = values.find(v => v.innerHTML === "0.00")

    if (zeroValue === undefined) {

        document.getElementById('checkForZeroValue').value = 0;
    } else {
        document.getElementById('checkForZeroValue').value = 1;
    }

}