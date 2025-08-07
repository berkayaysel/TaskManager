function getLoggedIncompanyId() {
  return parseInt(localStorage.getItem("companyId")) || null;
}

function submitRequest() {
  const header = document.getElementById('taskHeader').value.trim();
  const body = document.getElementById('requestInput').value.trim();
  const companyId = getLoggedIncompanyId();

  if (!companyId) {
    alert("Kullanıcı girişi yapılmamış. Lütfen tekrar giriş yapın.");
    return;
  }

  if (!header || !body) {
    alert("Lütfen başlık ve içerik girin.");
    return;
  }

  fetch('/user-dashboard/new', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      header: header,
      body: body,
      companyId: companyId
    })
  })
  .then(response => {
    if (response.ok) {
      alert("Talep başarıyla gönderildi.");
      document.getElementById('taskHeader').value = '';
      document.getElementById('requestInput').value = '';
    } else {
      alert("Gönderme başarısız oldu.");
    }
  })
  .catch(err => {
    console.error("Sunucu hatası:", err);
    alert("Sunucuya bağlanılamadı.");
  });
}

function changePassword() {
  window.location.href = '/user-dashboard/change-password';
}


function logout() {

  localStorage.removeItem("companyId");

  window.location.href = "http://localhost:8080/task/login";
}
