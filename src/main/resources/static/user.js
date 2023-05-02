const urlUser = '/api/info/', currentUser = fetch(urlUser).then(response => response.json());

// Заполнение шапки
currentUser.then(user => {
        let roles = '';
        user.roles.forEach(role => {
            roles += ' ';
            roles += role.name.replace("ROLE_", "");
        })
        document.getElementById("navbar-email").innerHTML = user.email;
        document.getElementById("navbar-roles").innerHTML = roles;
    }
)

// Заполнение таблицы пользователей
currentUser.then(user => {
        let roles = '';
        user.roles.forEach(role => {
            roles += ' ';
            roles += role.name.replace("ROLE_", "");
        })

        let result = '';
        result += `<tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                    <td>${roles}</td>
                   </tr>`;
        document.getElementById("user-info-table").innerHTML = result;
    }
)