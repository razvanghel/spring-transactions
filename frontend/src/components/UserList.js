import React, { useState, useEffect } from 'react';
import axios from 'axios';
import AccountsList from './AccountsList.js';

const UserList = () => {
    const [users, setUsers] = useState([]);
    const [accounts, setAccounts] = useState([]);
    const [selectedUser, setSelectedUser] = useState(null);

    const fetchUsers = async () => {
        try {
            const response = await axios.get('/users');
            setUsers(response.data);
        } catch (error) {
            console.error('Error fetching users:', error);
        }
    };

    const fetchUserAccounts = async (userId) => {
        try {
            const response = await axios.get(`/accounts/customer/${userId}`);
            setAccounts(response.data);
            setSelectedUser(userId);
        } catch (error) {
            console.error('Error fetching user accounts:', error);
        }
    };

    useEffect(() => {
        fetchUsers();
    }, []);

        return (
            <div>
                <h2>Users List</h2>
                {users.length > 0 ? (
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Surname</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {users.map(user => (
                                <tr key={user.id}>
                                    <td>{user.id}</td>
                                    <td>{user.name}</td>
                                    <td>{user.surname}</td>
                                    <td>
                                        <button onClick={() => fetchUserAccounts(user.id)}>
                                            Show Accounts
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                ) : (
                    <p>No users found.</p>
                )}
                {selectedUser && (
                <div>
                    <h3>Accounts for User ID: {selectedUser}</h3>
                     <AccountsList accounts={accounts} />
                 </div>
                )}
            </div>
        );
};

export default UserList;
