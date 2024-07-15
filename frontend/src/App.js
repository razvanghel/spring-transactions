import React, { useState } from 'react';
import CreateUserForm from './components/forms/CreateUserForm';
import UserList from './components/UserList';
import CreateAccountForm from './components/forms/CreateAccountForm';
import CreateTransactionForm from './components/forms/CreateTransactionForm';
const App = () => {
    const [refreshUsers, setRefreshUsers] = useState(false);
    const [refreshAccounts, setRefreshAccounts] = useState(false);
    const [refreshTransactions, setRefreshTransactions] = useState(false);

    const handleUserCreated = () => {
        alert('User created successfully');
        setRefreshUsers(!refreshUsers); // Toggle the refresh state to trigger re-fetching the user list
    };

    const handleAccountCreated = () => {
        alert('Account created successfully');
        setRefreshUsers(!refreshUsers);
        setRefreshAccounts(!refreshAccounts); // Toggle the refresh state to trigger re-fetching the account list
    };

    const handleTransactionCreated = () => {
        alert('Transaction created successfully');
        setRefreshTransactions(!refreshTransactions); // Toggle the refresh state to trigger re-fetching the transactions list
    };

    return (
        <div>
            <h1>Current Account Management</h1>
            <CreateUserForm onUserCreated={handleUserCreated} />
            <p></p>
            <CreateAccountForm onAccountCreated={handleAccountCreated} />
            <p></p>
            <CreateTransactionForm onTransactionCreated={handleTransactionCreated} />
            <p></p>

             <UserList key={refreshUsers} />
            <hr />
        </div>
    );
};

export default App;
