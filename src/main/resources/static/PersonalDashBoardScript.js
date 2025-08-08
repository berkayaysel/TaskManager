let reports = [];
let personnelList = []; // Personel listesi burada tutulur
const CURRENT_USER_ID = localStorage.getItem("companyId") || null;

document.addEventListener('DOMContentLoaded', () => {
    if (CURRENT_USER_ID) {
        fetchAssignedReports();
    } else {
        window.location.href = "http://localhost:8080/task/login";
    }
});

function fetchAssignedReports() {
    fetch(`http://localhost:8080/personal-dashboard/reports/${CURRENT_USER_ID}`)
        .then(res => {
            if (!res.ok) throw new Error("Atanan raporlar alınamadı");
            return res.json();
        })
        .then(data => {
            reports = data;
            renderTable();
        })
        .catch(err => {
            console.error("Atanan raporlar alınamadı:", err);
            alert("Raporlar yüklenirken bir hata oluştu.");
        });
}

function renderTable() {
    const tbody = document.getElementById('reportBody');
    tbody.innerHTML = '';

    reports.forEach(report => {
        const row = document.createElement('tr');
        const statusClass = `status-${report.status.toLowerCase()}`;

        row.innerHTML = `
            <td>${report.id}</td>
            <td>${report.header}</td>
            <td>${report.body}</td>
            <td><span class="status-badge ${statusClass}">${report.status}</span></td>
            <td>
                <div><strong>Atandı:</strong> ${report.atanma_date ?? '-'}</div>
                <div><strong>Kabul:</strong> ${report.kabul_date ?? '-'}</div>
                <div><strong>Reddedildi:</strong> ${report.iptal_date ?? '-'}</div>
            </td>
            <td>
                <button class="accept-btn" onclick="acceptReport(${report.id})">Görevi Kabul Et</button>
                <button class="reject-btn" onclick="rejectReport(${report.id})">Görevi Reddet</button>
            </td>
        `;

        tbody.appendChild(row);
    });
}

function acceptReport(reportId) {
    if (!confirm("Bu görevi kabul etmek istediğinize emin misiniz?")) return;

    fetch(`http://localhost:8080/personal-dashboard/reports/${reportId}/accept`, {
        method: 'POST'
    })
    .then(res => {
        if (!res.ok) throw new Error("Sunucu hatası");
        return res.json();
    })
    .then(updatedReport => {
        const idx = reports.findIndex(r => r.id === updatedReport.id);
        reports[idx] = updatedReport;
        alert(`Görev ${reportId} kabul edildi.`);
        renderTable();
    })
    .catch(e => {
        console.error(e);
        alert("Görevi kabul ederken hata oluştu.");
    });
}

function rejectReport(reportId) {
    if (!confirm("Bu görevi reddetmek istediğinize emin misiniz?")) return;

    fetch(`http://localhost:8080/personal-dashboard/reports/${reportId}/reject`, {
        method: 'POST'
    })
    .then(res => {
        if (!res.ok) throw new Error("Sunucu hatası");
        return res.json();
    })
    .then(updatedReport => {
        const idx = reports.findIndex(r => r.id === updatedReport.id);
        reports[idx] = updatedReport;
        alert(`Görev ${reportId} reddedildi.`);
        renderTable();
    })
    .catch(e => {
        console.error(e);
        alert("Görevi reddederken hata oluştu.");
    });
}

// UserDashBoardScript.js dosyasından kopyalanacak
function changePassword() {
  window.location.href = '/user-dashboard/change-password';
}

function logout() {
  localStorage.removeItem("companyId");
  window.location.href = "http://localhost:8080/task/login";
}