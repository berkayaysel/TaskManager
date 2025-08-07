// script.js

document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login-form');
    const messageDiv = document.getElementById('message');

    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault(); // Sayfanın normalde yeniden yüklenmesini engelle

        // Formdan verileri al
        const companyId = document.getElementById('company_id').value;
        const password = document.getElementById('password').value;

        // Backend'e gönderilecek veriyi JSON formatına hazırla
        const data = {
            company_id: parseInt(companyId), // company_id'yi integer'a dönüştür
            password: password
        };

        console.log('Gönderilecek veri:', data);

        // API'ye POST isteği gönder
        try {
            // Backend endpoint'iniz
            const response = await fetch('http://localhost:8080/task/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data), // JavaScript nesnesini JSON string'ine dönüştür
            });

            // Yanıtı işle
            if (response.ok) {
                const result = await response.json();


                localStorage.setItem("companyId", result.companyId); // veya companyId

                // Yönlendirme yap
                if (result.redirectUrl) {
                    window.location.href = result.redirectUrl;
                } else {
                    messageDiv.textContent = 'Giriş başarılı!';
                    messageDiv.style.color = 'green';
                }
            } else {
                const errorText = await response.text();
                messageDiv.textContent = `Giriş başarısız: ${errorText}`;
                messageDiv.style.color = 'red';
            }
        } catch (error) {
            messageDiv.textContent = 'Bir hata oluştu: ' + error.message;
            messageDiv.style.color = 'red';
        }
    });
});