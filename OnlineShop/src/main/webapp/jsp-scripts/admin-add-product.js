function validateNewProductForm() {
    let categoryName = document.getElementById('name');
    let price = document.getElementById('price');
    let productDescription = document.getElementById('description');
    let categoryImagePath = document.getElementById('imagePath');
    let categoryImagePathValue = 'images/' + categoryImagePath.value;
    if (categoryName.value === '' || categoryName.value.length > 45) {
        categoryName.style.border = 'solid';
        categoryName.style.borderColor = '#FF0000';
        return false;
    } else {
        categoryName.style.border = 'none';
        categoryName.style.borderColor = 'transparent';
    }
    try {
        if (parseFloat(price.value) <= 0) {
            price.style.border = 'solid';
            price.style.borderColor = '#FF0000';
            return false;
        } else {
            price.style.border = 'none';
            price.style.borderColor = 'transparent';
        }
    } catch (error) {
        price.style.border = 'solid';
        price.style.borderColor = '#FF0000';
        return false;
    }
    if (productDescription.value === '') {
        productDescription.style.border = 'solid';
        productDescription.style.borderColor = '#FF0000';
        return false;
    } else {
        productDescription.style.border = 'none';
        productDescription.style.borderColor = 'transparent';
    }
    if (categoryImagePath.value === '' ||categoryImagePathValue.length > 70) {
        categoryImagePath.style.border = 'solid';
        categoryImagePath.style.borderColor = '#FF0000';
        return false;
    } else {
        categoryImagePath.style.border = 'none';
        categoryImagePath.style.borderColor = 'transparent';
    }

}