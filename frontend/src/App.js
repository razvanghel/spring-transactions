import React, { useState } from 'react';
import UserList from './components/UserList';
import DeleteForm from './components/forms/DeleteForm';
import CreateForm from './components/forms/CreateForm';

const App = () => {
    const [refreshUsers, setRefreshUsers] = useState(false);
    const [refreshTransactions, setRefreshTransactions] = useState(false);

    const handleUserCreated = () => {
        setRefreshUsers(!refreshUsers); // Toggle the refresh state to trigger re-fetching the user list
    };

    const handleEntityDeleted = () => {
        setRefreshUsers(!refreshUsers); // Refresh user list to reflect changes
    };

    return (
        <div>
            <p>
                        Here are some use cases to try and see the exception handling:
                        <ul>
                            <li>Create an account with a negative balance</li>
                            <li>Create a transaction with a zero or negative amount</li>
                            <li>Delete an account/user/transaction with an unknown ID</li>
                        </ul>
                    </p>
        <h1>Create user form</h1>
            <CreateForm onCreated={handleUserCreated} />

            <br></br>
           <h2>Delete form</h2>

            <DeleteForm onEntityDeleted={handleEntityDeleted} />
            <br></br>
            <br></br>
            <UserList key={refreshUsers} />
            <hr />
        </div>
    );
};

export default App;
