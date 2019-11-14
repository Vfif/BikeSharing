function moveToAnotherPage() {
    let form = document.createElement('form');
    form.action = '${pageContext.request.contextPath}/controller';
    form.method = 'post';
    form.innerHTML = '<input type="hidden" name="command" value="logout"/>';
    document.body.append(form);
    form.submit();
}