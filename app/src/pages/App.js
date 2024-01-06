import { useState, useEffect } from "react";
import Header from "../containers/Header";
import Sidebar from "../containers/Sidebar";
import View from "../containers/View";


function App() {

  const [communities, setCommunities] = useState([])
  const [selectedCommunityId, setSelectedCommunityId] = useState(null)
  const [selectedCommunity, setSelectedCommunity] = useState(null)

  const handleCommunityClick = (id) => {
    setSelectedCommunityId(id)
  }

  useEffect(() => {
    fetch('http://localhost:8080/api/communities')
      .then(response => response.json())
      .then(data => setCommunities(data))
  }, [])

  useEffect(() => {
    fetch('http://localhost:8080/api/community/' + selectedCommunityId)
      .then(response => {
        if (!response.ok) {
          throw new Error('Selected community not found')
        }
        return response.json()
      })
      .then(data => {
        setSelectedCommunity(data)
        console.log(data) 
      })
      .catch(error => {
        console.log(error.message)
        setSelectedCommunity(null)
      })
  }, [selectedCommunityId])


  return (
    <>
      <Header />
      <Sidebar communities={communities} onCommunityClick={handleCommunityClick} />
      <View community={selectedCommunity}/>
    </>
  );
}

export default App;
