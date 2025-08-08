let reports = [];

const CURRENT_USER_ID = parseInt(localStorage.getItem("userId"), 10) || 1;


function renderTable() {
  const tbody = document.getElementById('reportBody');
  tbody.innerHTML = '';

  reports.forEach(report => {
    const row = document.createElement('tr');
    const statusClass = `status-${report.status}`;


    row.innerHTML = `
      <td>${report.id}</td>
      <td>${report.header}</td>
      <td>${report.body}</td>
      <td><span class="status-badge ${statusClass}">${report.status}</span></td>

      <td>
    <td>
        <div><strong>Oluşturuldu:</strong> ${report.talep_date ?? '-'}</div>
        <div><strong>Atandı:</strong>   ${report.atanma_date ?? '-'}</div>
        <div><strong>Tamamlandı:</strong>${report.bitis_date ?? '-'}</div>
        <div><strong>Reddedildi:</strong>${report.iptal_date ?? '-'}</div>
    </td>
      </td>
      <td>
        <select id="personnelSelect-${report.id}">
          <option value="">Seçiniz</option>
          ${personnelList.map(p => `
            <option value="${p.id}" ${p.id === report.personalId ? 'selected' : ''}>
              ${p.name} ${p.surName}
            </option>
          `).join('')}
        </select>
        <button class="assign-btn" onclick="assignPersonnel(${report.id})">Tamam</button>
      </td>
      <td>
        <button class="complete-btn" onclick="completeReport(${report.id})">Tamamlandı</button>
        <button class="reject-btn" onclick="rejectReport(${report.id})">Talebi Reddet</button>
      </td>
    `;

    tbody.appendChild(row);
  });
}

function assignPersonnel(reportId) {   //TODO: contoller servis katmanları yazılıp çağrılacak
  const select = document.getElementById(`personnelSelect-${reportId}`);
  const personnelId = parseInt(select.value);
  if (!personnelId) { alert("Lütfen bir personel seçin."); return; }

  fetch(`http://localhost:8080/admin-dashboard/reports/${reportId}/assign?actorId=${personnelId}`, {
    method: 'POST'
  })
  .then(res => {
    if (!res.ok) throw new Error("Sunucu hatası");
    return res.json();
  })
  .then(updatedReport => {
    const idx = reports.findIndex(r => r.id === updatedReport.id);
    reports[idx] = updatedReport;
    alert(`Görev ${reportId} atandı.`);
    renderTable();
  })
  .catch(e => {
    console.error(e);
    alert("Atama sırasında hata oluştu");
  });


  const report = reports.find(r => r.id === reportId);
  report.assignedPersonnelId = personnelId;
  report.status = 'atandı';
  report.assignedAt = new Date().toISOString().slice(0, 16).replace('T', ' ');


  alert(`Görev ${reportId}, ${personnelList.find(p => p.id === personnelId).name} adlı personele atandı.`);
  // TODO: fetch('/admin/assign', ...) ile backend'e gönder
  renderTable();
}


function filterReports() {
  const filter = document.getElementById('statusFilter').value;
  if (filter === 'Tümü') {
    renderTable();
  } else {
    const filtered = reports.filter(r => r.status === filter);
    renderFilteredTable(filtered);
  }
}

function renderFilteredTable(filteredReports) {
  const tbody = document.getElementById('reportBody');
  tbody.innerHTML = '';
  filteredReports.forEach(report => {
    const statusClass = `status-${report.status}`;
    const row = document.createElement('tr');
    row.innerHTML = `
      <td>${report.id}</td>
      <td>${report.header}</td>
      <td><span class="status-badge ${statusClass}">${report.status}</span></td>
      <td>
        <div><strong>Oluşturuldu:</strong> ${report.createdAt}</div>
        ${report.assignedAt ? `<div><strong>Atandı:</strong> ${report.assignedAt}</div>` : ''}
        ${report.completedAt ? `<div><strong>Tamamlandı:</strong> ${report.completedAt}</div>` : ''}
      </td>
      <td>
        <select onchange="assignPersonnel(${report.id}, this.value)">
          <option value="">Seçiniz</option>
          ${personnelList.map(p => `
            <option value="${p.id}" ${p.id === report.assignedPersonnelId ? 'selected' : ''}>
              ${p.name}
            </option>
          `).join('')}
        </select>
      </td>
    `;
    tbody.appendChild(row);
  });
}

document.addEventListener('DOMContentLoaded', () => {
  fetchPersonnelList();
  fetchReports();
});




function rejectReport(reportId) {
  if (!confirm("Bu görevi reddetmek istediğinize emin misiniz?")) return;

  fetch(`http://localhost:8080/admin-dashboard/reports/${reportId}/reject`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' }
  })
  .then(res => {
    if (!res.ok) throw new Error('Sunucuda hata oluştu');
    return res.json();
  })
  .then(updatedReport => {
    // Front-end listemizi güncelle
    const idx = reports.findIndex(r => r.id === updatedReport.id);
    reports[idx] = updatedReport;
    alert(`Görev ${reportId} IPTAL.`);
    renderTable();
  })
  .catch(err => {
    console.error(err);
    alert('Reddetme sırasında bir hata oluştu.');
  });
}

function changePassword() {
  window.location.href = '/user-dashboard/change-password';
}

function logout() {
  localStorage.removeItem("companyId");
  window.location.href = "http://localhost:8080/task/login";
}
let personnelList = [];

function fetchPersonnelList() {
  fetch("http://localhost:8080/admin-dashboard/all-users")
    .then(res => res.json())
    .then(data => {
      personnelList = data;
      renderTable();
    })
    .catch(err => {
      console.error("Personel listesi alınamadı:", err);
    });
}

function fetchReports() {
  fetch("http://localhost:8080/admin-dashboard/all-reports")
    .then(res => res.json())
    .then(data => {
      reports = data;
      renderTable();
    })
    .catch(err => {
      console.error("Raporlar alınamadı:", err);
    });
}
function completeReport(reportId) {
  if (!confirm("Bu görevi tamamlamak istediğinize emin misiniz?")) return;

  const actorId = CURRENT_USER_ID; // Oturum açmış admin/personal ID’si

  fetch(`http://localhost:8080/admin-dashboard/reports/${reportId}/complete?actorId=${actorId}`, {
    method: 'POST'
  })
  .then(res => {
    if (!res.ok) throw new Error('Sunucuda hata oluştu');
    return res.json();
  })
  .then(updatedReport => {
    const idx = reports.findIndex(r => r.id === updatedReport.id);
    reports[idx] = updatedReport;
    alert(`Görev ${reportId} tamamlandı.`);
    renderTable();
  })
  .catch(err => {
    console.error(err);
    alert('Tamamlama sırasında bir hata oluştu.');
  });
}

function changePassword() {
  window.location.href = '/user-dashboard/change-password';
}


function logout() {

  localStorage.removeItem("companyId");

  window.location.href = "http://localhost:8080/task/login";
}
