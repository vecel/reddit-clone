import { useState, useEffect } from "react";
import Header from "../containers/Header";
import Sidebar from "../containers/Sidebar";
import View from "../containers/View";


function App() {

  const [communities, setCommunities] = useState([])
  const [selectedCommunityId, setSelectedCommunityId] = useState(-1)

  const handleCommunityClick = (id) => {
    setSelectedCommunityId(id)
  }

  useEffect(() => {
    fetch('http://localhost:8080/api/communities')
      .then(response => response.json())
      .then(data => setCommunities(data))
  }, [])

  return (
    <>
      <Header />
      <Sidebar communities={communities} onCommunityClick={handleCommunityClick} />
      <View communityId={selectedCommunityId}/>
    </>
  );
}

export default App;
