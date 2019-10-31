import React from 'react';

class InfosPlayer extends React.Component{

  constructor(props) {
    super(props) 
        this.state = {

        }
    
}

  render() {
    return (
<div className={this.props.className}> 
    {this.props.playerList.map((i, index) => {
      return <p key={index}>Joueur: {i.playerName} Fourmi Renfort: {i.playerAvailableAnts}</p>
    })}
</div>
    )
  }
}

export default InfosPlayer;