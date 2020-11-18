import React, { Component } from 'react';


class Message extends Component{
    constructor (props){
        super(props);


    }
    f(){
        var str=this.txt.value
        str= str.replace("🙂","#souriant").replace("😀","#content").replace("😉","#clindoeil").replace("🤪","#foufou").replace("😟","#triste").replace("😭","#pleure")

        this.props.addTweet(str)
        document.getElementById("exampleFormControlTextarea1").value="";

    }
    addEmoji(label){
        document.getElementById("exampleFormControlTextarea1").value+=label;

    }
    render(){
        return(
            <div className="form-group" >
                <textarea className="form-control" id="exampleFormControlTextarea1" name="exampleFormControlTextarea1"
                                              placeholder="Nouveau message? Ecrivez-le" rows="3" cols="150" ref={texte => this.txt =texte}></textarea>
                <div className="btn-group mr-2" role="group" aria-label="First group">
                    <button type="button" className="btn btn-secondary"><span role="img" aria-label="content" onClick={()=>this.addEmoji("😀")}>😀</span></button>
                    <button type="button" className="btn btn-secondary"><span role="img" aria-label="souriant" onClick={()=>this.addEmoji("🙂")}>🙂</span></button>
                    <button type="button" className="btn btn-secondary"><span role="img" aria-label="clindoeil" onClick={()=>this.addEmoji("😉")}>😉</span></button>
                    <button type="button" className="btn btn-secondary"><span role="img" aria-label="foufou" onClick={()=>this.addEmoji("🤪")}>🤪</span></button>
                    <button type="button" className="btn btn-secondary"><span role="img" aria-label="triste" onClick={()=>this.addEmoji("😟")}>😟</span></button>
                    <button type="button" className="btn btn-secondary"><span role="img" aria-label="pleure" onClick={()=>this.addEmoji("😭")}>😭</span></button>


                </div>
                <button className="btn btn-info" type="submit" style={{position:'auto', left: '89%'}} onClick={()=>this.f()}>Poster !</button>


            </div>


        )
    }



}
export default Message;