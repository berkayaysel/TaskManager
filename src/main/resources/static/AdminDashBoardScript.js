const tasks = [];
const users = {};  // basit kullanıcı/task istatistiği için

function searchUser() {
  const name = document.getElementById('searchUser').value;
  const user = users[name];
  const userInfo = document.getElementById('userInfo');
  if (user) {
    userInfo.innerText = `Kullanıcı: ${name}\nTask Sayısı: ${user.createdTasks || 0}`;
  } else {
    userInfo.innerText = "Kullanıcı bulunamadı.";
  }
}

function changePassword() {
  const username = document.getElementById('username').value;
  const newPassword = document.getElementById('newPassword').value;
  alert(`"${username}" adlı kullanıcının şifresi "${newPassword}" olarak değiştirildi (örnek).`);
}

function assignTask() {
  const sender = document.getElementById('taskUser').value;
  const assignee = document.getElementById('assignTo').value;
  const description = document.getElementById('taskDescription').value;
  const timestamp = new Date().toLocaleString();

  const task = {
    description,
    sender,
    assignee,
    status: 'Yapılıyor',
    timestamp
  };

  tasks.push(task);

  // Kullanıcı istatistiği
  users[sender] = users[sender] || {};
  users[sender].createdTasks = (users[sender].createdTasks || 0) + 1;

  users[assignee] = users[assignee] || {};
  users[assignee].completedTasks = users[assignee].completedTasks || 0;

  renderTasks();
  renderStats();
}

function renderTasks() {
  const tbody = document.querySelector('#taskTable tbody');
  tbody.innerHTML = '';

  tasks.forEach((task, index) => {
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td>${task.description}</td>
      <td>${task.sender}</td>
      <td>${task.assignee}</td>
      <td>
        <select onchange="updateStatus(${index}, this.value)">
          <option value="Yapılıyor" ${task.status === 'Yapılıyor' ? 'selected' : ''}>Yapılıyor</option>
          <option value="Bitti" ${task.status === 'Bitti' ? 'selected' : ''}>Bitti</option>
          <option value="İptal Edildi" ${task.status === 'İptal Edildi' ? 'selected' : ''}>İptal Edildi</option>
        </select>
      </td>
      <td>${task.timestamp}</td>
    `;
    tbody.appendChild(tr);
  });
}

function updateStatus(index, newStatus) {
  tasks[index].status = newStatus;

  // İstatistik güncelle
  if (newStatus === 'Bitti') {
    const assignee = tasks[index].assignee;
    users[assignee].completedTasks = (users[assignee].completedTasks || 0) + 1;
  }

  renderStats();
}

function renderStats() {
  const statsDiv = document.getElementById('stats');
  statsDiv.innerHTML = '';

  Object.keys(users).forEach(user => {
    const u = users[user];
    statsDiv.innerHTML += `
      <p><strong>${user}</strong>:
        Oluşturulan Task: ${u.createdTasks || 0},
        Tamamlanan Task: ${u.completedTasks || 0}</p>
    `;
  });
}
