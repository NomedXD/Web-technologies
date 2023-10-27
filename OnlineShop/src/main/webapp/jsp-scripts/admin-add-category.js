function validateNewCategoryForm() {
    let categoryName = document.getElementById('name');
    let categorySomeText = document.getElementById('someText');
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
    if (categorySomeText.value === '' || categorySomeText.value.length > 45) {
        categorySomeText.style.border = 'solid';
        categorySomeText.style.borderColor = '#FF0000';
        return false;
    } else {
        categorySomeText.style.border = 'none';
        categorySomeText.style.borderColor = 'transparent';
    }
    if (categoryImagePath.value === '' ||categoryImagePathValue.length > 45) {
        categoryImagePath.style.border = 'solid';
        categoryImagePath.style.borderColor = '#FF0000';
        return false;
    } else {
        categoryImagePath.style.border = 'none';
        categoryImagePath.style.borderColor = 'transparent';
    }

}