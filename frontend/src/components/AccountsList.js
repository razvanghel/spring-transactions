
import React, { useState, useEffect } from 'react';
import TransactionList from './TransactionList.js';
import axios from 'axios';

import CreateTransactionForm from './forms/CreateTransactionForm';
const AccountsList = ({ userId }) => {
    const [accounts, setAccounts] = useState([]);
    const [selectedAccount, setSelectedAccount] = useState(null);
        const [refreshTransactions, setRefreshTransactions] = useState(false);

    const fetchAccounts = async (userId) => {
        try {
            const response = await axios.get(`/accounts/customer/${userId}`);
            setAccounts(response.data);// Corrected the setter function
        } catch (error) {
            console.error('Error fetching user accounts:', error);
        }
    };

    const handleTransactionCreated = () => {
        alert('Transaction created successfully');
        setRefreshTransactions(!refreshTransactions)
    };

    useEffect(() => {
        fetchAccounts(userId);
    }, [userId]);

    return (
           <div>
           <h2>Create transactions</h2>
               <CreateTransactionForm onTransactionCreated={handleTransactionCreated} />

               <h3>Accounts for User ID: {userId}</h3>
               {accounts.length > 0 ? (
                   <table>
                       <thead>
                           <tr>
                               <th>Account ID</th>
                               <th>Balance</th>
                               <th>Actions</th>
                           </tr>
                       </thead>
                       <tbody>
                           {accounts.map(account => (
                               <tr key={account.id}>
                                   <td>{account.id}</td>
                                   <td>{account.balance}</td>
                                   <td>
                                       <button onClick={() => setSelectedAccount(account.id)}>
                                           Show Transactions
                                       </button>
                                   </td>
                               </tr>
                           ))}
                       </tbody>
                   </table>
               ) : (
                   <p>No accounts found.</p>
               )}
               {selectedAccount &&
                    <TransactionList key={handleTransactionCreated} accountId={selectedAccount} />
               }
           </div>
       );
};

export default AccountsList;
