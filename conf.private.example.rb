@bot.configure do |c|
    c.plugins.options[Steph::Identify] = {
        type: :nickserv,
        password: ""
    }
end
