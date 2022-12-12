import React, {useState, useCallback} from "react";
import { useFocusEffect } from "@react-navigation/native";
import {View, Text, StyleSheet} from 'react-native'

import { gameInterface } from "../interfaces/gameInterface";
import { clientGetWinner } from "../api/api";
import { Winner } from "../components/Winner/Winner";

export function WinnerScreen(){

    const [game, setGame] = useState<gameInterface>(
        {
            id: 0,
            name: "--",
            description: "--",
            cover: "",
            votes: 0,
        }
    )

    useFocusEffect(
        useCallback(() => {
            (async () => {
                const winner = await clientGetWinner()
                setGame(winner)
                console.log(winner)
            })();
        },[])
    )

    return (
        <View style={styles.container}>
            <Winner 
                name={game.name}
                cover={game.cover}
                votes={game.votes}
            />
        </View>
    )
}

const styles = StyleSheet.create({
    container:{
        flex: 1,
        color:'#fff',
        backgroundColor: '#191919',
        alignItems:'center',
        justifyContent:'center'
    }
})