import React, { useState } from 'react';
import CreateUserForm from './components/forms/CreateUserForm';
import UserList from './components/UserList';
import CreateAccountForm from './components/forms/CreateAccountForm';
import DeleteForm from './components/forms/DeleteForm';
const App = () => {
    const [refreshUsers, setRefreshUsers] = useState(false);
    const [refreshTransactions, setRefreshTransactions] = useState(false);

    const handleUserCreated = () => {
        alert('User created successfully');
        setRefreshUsers(!refreshUsers); // Toggle the refresh state to trigger re-fetching the user list
    };

    const handleEntityDeleted = () => {
        setRefreshUsers(!refreshUsers); // Refresh user list to reflect changes
    };

    return (
        <div>
            <h1>Create user form</h1>
            <CreateUserForm onUserCreated={handleUserCreated} />
            <p></p>
           <h2>Delete form</h2>

            <DeleteForm onEntityDeleted={handleEntityDeleted} />
                        <p></p>

            <UserList key={refreshUsers} />
            <hr />
        </div>
    );
};

export default App;
