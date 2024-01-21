import { useState, useEffect } from "react";
import Header from "../containers/Header";
import Sidebar from "../containers/Sidebar";
import View from "../containers/View";


function App() {

  const [communities, setCommunities] = useState([])
  const [selectedCommunityId, setSelectedCommunityId] = useState(-1)
  const [loggedUser, setLoggedUser] = useState(null)
  const [loading, setLoading] = useState(true)

  const handleCommunityClick = (id) => {
    setSelectedCommunityId(id)
  }

  const handleLogout = () => {
      fetch('http://localhost:8080/api/logout', {
          method: 'GET',
          credentials: 'include'
      })
      .then(() => setLoggedUser(null))
  }

  useEffect(() => {
    fetch('http://localhost:8080/api/communities')
      .then(response => response.json())
      .then(data => setCommunities(data))

    fetch('http://localhost:8080/api/loggedUser', {
      credentials: 'include'
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('No logged user')
      }
      return response.json()
    })
    .then(data => setLoggedUser(data))
    .then(() => setLoading(false))
    .catch(err => {
      console.log(err.message)
      setLoading(false)
    })
  }, [])

  return (
    (loading) ? <div>Loading...</div> :
    <>
      <Header loggedUser={loggedUser} onLogout={handleLogout}/>
      <Sidebar communities={communities} onCommunityClick={handleCommunityClick} />
      <View communityId={selectedCommunityId} loggedUser={loggedUser}/>
    </>
  );
}

export default App;
